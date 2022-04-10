package SwordMaster.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import SwordMaster.SwordMasterMod;
import SwordMaster.actions.ApplyStunSelfAction;
import SwordMaster.characters.swordMaster;
import SwordMaster.powers.FlowingStance;

public class UltimateSlayTempest extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(UltimateSlayTempest.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Ultimate_Slay_Tempest.png");
    private static final int COST = 3;
    private static final CardType CARD_TYPE = CardType.ATTACK;
    private static final CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public UltimateSlayTempest() {
        // 卡牌ID，卡牌名称，图片路径，费用，描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 10;
        this.baseDamage = 2;
        this.baseBlock = 20;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        // 卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));

        for (int i = 0; i < this.baseMagicNumber; i++) {
            if (i == 0) {
                addToBot(new SFXAction("ATTACK_WHIRLWIND"));
                addToBot(new VFXAction(new WhirlwindEffect(), 0.0f));
            }
            addToBot(new SFXAction("ATTACK_HEAVY"));
            addToBot(new VFXAction(p, new CleaveEffect(), 0.0f));
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageType,
                    AbstractGameAction.AttackEffect.NONE, true));
        }

        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
        if (!p.hasPower(FlowingStance.POWER_ID)) {
            // 有流心效果，抵消debuff
            addToBot(new ApplyStunSelfAction(p, p, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new UltimateSlayTempest();
    }
}
