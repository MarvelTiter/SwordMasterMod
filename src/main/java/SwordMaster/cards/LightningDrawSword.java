package SwordMaster.cards;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SwordMaster.SwordMasterMod;
import SwordMaster.characters.swordMaster;
import SwordMaster.powers.ElectricShock;

public class LightningDrawSword extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(LightningDrawSword.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Lightning_Draw_Sword.png");
    private static final int COST = 2;
    private static final int ATTACK_DMG = 20;
    private static final int UPGRADE_DMG = 4;
    private static final int MAGIC_NUMBER = 3;
    private static final CardType CARD_TYPE = CardType.ATTACK;
    private static final CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public LightningDrawSword() {
        // 卡牌ID，卡牌名称，图片路径，费用，描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = MAGIC_NUMBER;
        this.isMultiDamage = true;
    }

    @Override
    public void upgrade() {
        // 卡牌升级后的效果
        if (!this.upgraded) {
            // 更改名字和提高3点伤害
            upgradeName();
            upgradeDamage(UPGRADE_DMG);
            upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator<AbstractMonster> it = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        int monsterCount = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        int turnDamage = this.baseDamage - (monsterCount - 1) * this.baseMagicNumber;
        while (it.hasNext()) {
            AbstractMonster mo = it.next();
            if (mo.hasPower(ElectricShock.POWER_ID)) {
                damage = (int) (turnDamage * 1.5);
            }
            calculateCardDamage(mo);
            DamageInfo dInfo = new DamageInfo(p, damage, this.damageTypeForTurn);
            AbstractGameAction action = new DamageAction(mo, dInfo, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            addToBot(action);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LightningDrawSword();
    }
}
