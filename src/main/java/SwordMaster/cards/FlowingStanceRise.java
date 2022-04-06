package SwordMaster.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SwordMaster.SwordMasterMod;
import SwordMaster.characters.swordMaster;
import SwordMaster.powers.FlowingStance;
import SwordMaster.powers.FlowingStanceForcePower;
import SwordMaster.utils.StatusManage;

public class FlowingStanceRise extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(FlowingStanceRise.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Flowing_Stance_Rise.png");
    private static final int COST = 1;
    private static final int ATTACK_DMG = 4;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_PLUS_MAGIC = 1;
    private static final CardType CARD_TYPE = CardType.ATTACK;
    private static final CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public FlowingStanceRise() {
        // 卡牌ID，卡牌名称，图片路径，费用，描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.tags.add(swordMaster.Enums.FlowingForce);
        this.tags.add(swordMaster.Enums.FlowingStance);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = MAGIC_NUMBER;
    }

    @Override
    public void upgrade() {
        // 卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.baseMagicNumber; i++) {
            DamageInfo dInfo = new DamageInfo(p, this.damage, this.damageTypeForTurn);
            AbstractGameAction action = new DamageAction(m, dInfo, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            addToBot(action);
        }
        ReduceFlowingStance(p);
    }

    // 流心
    StatusManage s = new StatusManage();
    // 流心狂
    StatusManage f = new StatusManage();

    @Override
    public void applyPowers() {
        super.applyPowers();

        int sc = s.getNewValue(hasStance());
        upgradeMagicNumber(sc);

        int sf = f.getNewValue(hasStanceForce());
        upgradeMagicNumber(sf);

        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlowingStanceRise();
    }
}
