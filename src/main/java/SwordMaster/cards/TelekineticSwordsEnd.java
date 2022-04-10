package SwordMaster.cards;

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
import SwordMaster.relics.AttackCounter;

public class TelekineticSwordsEnd extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID(TelekineticSwordsEnd.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Telekinetic_Swords_End.png");
    private static final int COST = 0;
    private static final int ATTACK_DMG = 1;
    private static final int UPGRADE_PLUS_DMG = 1;
    private static final CardType CARD_TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public TelekineticSwordsEnd() {
        // 卡牌ID，卡牌名称，图片路径，费用，描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = ATTACK_DMG;
        this.exhaust = true;
        this.selfRetain = true;
        this.baseMagicNumber = getAttackCount();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.baseMagicNumber; i++) {
            DamageInfo dInfo = new DamageInfo(p, this.damage, this.damageTypeForTurn);
            AbstractGameAction action = new DamageAction(m, dInfo, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            addToBot(action);
        }
    }

    @Override
    public void applyPowers() {
        this.baseMagicNumber = getAttackCount() / 2;
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
