package SwordMaster.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
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

public class UpwardSlash extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID("UpwardSlash");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Upward_Slash.png");
    private static final int COST = 0;
    private static final CardType CARD_TYPE = CardType.ATTACK;
    private static final CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public UpwardSlash() {
        // 卡牌ID，卡牌名称，图片路径，费用，描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
        this.baseDamage = 4;
        this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        // 卡牌升级后的效果
        if (!this.upgraded) {
            // 更改名字和提高3点伤害
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo dInfo = new DamageInfo(p, this.damage, this.damageTypeForTurn);
        AbstractGameAction action = new DamageAction(m, dInfo, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        AbstractDungeon.actionManager.addToBottom(action);
        addToBot(new ApplyPowerAction(m, p, new ElectricShock(m, 1), 1, true));
        if (this.upgraded) {
            ApplyFlowingStance(p);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new UpwardSlash();
    }
}
