package SwordMaster.cards;

import SwordMaster.actions.FlowingStanceRiseAction;
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
        this.tags.add(swordMaster.Enums.FlowingMagic);
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
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(CustomAttackAction(p,m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        ReduceFlowingStance(p);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int amt = 0;
        if (hasStance()){
            amt += 1;
        }
        if (hasStanceForce()){
            amt += 1;
        }
        this.magicNumber = baseMagicNumber + amt;
        isMagicNumberModified = true;
        initializeDescription();
    }
}
