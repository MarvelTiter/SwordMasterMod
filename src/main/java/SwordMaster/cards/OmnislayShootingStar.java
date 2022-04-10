package SwordMaster.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SwordMaster.SwordMasterMod;
import SwordMaster.characters.swordMaster;
import SwordMaster.utils.StatusManage;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class OmnislayShootingStar extends Master_AbstractCard {
    public static final String ID = SwordMasterMod.makeID("OmnislayShootingStar");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = SwordMasterMod.makeCardPath("Omnislay_Shooting_Star.png");
    private static final int COST = 3;
    private static final CardType CARD_TYPE = CardType.SKILL;
    private static final CardColor COLOR = swordMaster.Enums.COLOR_LIGHT_BLUE;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public OmnislayShootingStar() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CARD_TYPE, COLOR, RARITY, TARGET);
        this.tags.add(swordMaster.Enums.FlowingStance);
        this.tags.add(swordMaster.Enums.FlowingEnergy);
        this.exhaust = true;
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new StunMonsterAction(mo, p, 1));
        }
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override // com.megacrit.cardcrawl.cards.AbstractCard
    public AbstractCard makeCopy() {
        return new OmnislayShootingStar();
    }
}
