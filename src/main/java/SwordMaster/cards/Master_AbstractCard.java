package SwordMaster.cards;

import basemod.abstracts.CustomCard;
import basemod.interfaces.OnPlayerTurnStartSubscriber;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import SwordMaster.powers.FlowingStance;
import SwordMaster.powers.FlowingStanceForcePower;
import SwordMaster.relics.AttackCounter;
import SwordMaster.actions.ApplyFlowingStanceAction;
import SwordMaster.actions.ReduceFlowingStanceAction;
import SwordMaster.characters.swordMaster;

public abstract class Master_AbstractCard extends CustomCard implements OnPlayerTurnStartSubscriber {
    public Master_AbstractCard(String id, String name, String img, int cost, String rawDescription, CardType type,
            CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    /**
     * 获得一层流心buff
     * 
     * @param p
     */
    public void ApplyFlowingStance(AbstractPlayer p) {
        addToBot(new ApplyFlowingStanceAction(p));
    }

    /**
     * 使用后失去一层流心buff
     */
    public void ReduceFlowingStance(AbstractPlayer p) {
        if (!hasStance())
            return;
        addToTop(new ReduceFlowingStanceAction(p));
    }

    /**
     * 检查是否有流心buff
     * 
     * @return
     */
    public boolean hasStance() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null)
            return false;
        return player.hasPower(FlowingStance.POWER_ID);
    }

    /**
     * 检查是否有流心狂buff
     * 
     * @return
     */
    public boolean hasStanceForce() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null)
            return false;
        return player.hasPower(FlowingStanceForcePower.POWER_ID);
    }

    /**
     * 获取本场战斗攻击次数
     * 
     * @return
     */
    public int getAttackCount() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null) {
            return 0;
        }
        AbstractRelic counter = player.getRelic(AttackCounter.ID);
        return counter.counter;
    }

    @Override
    public void triggerOnGlowCheck() {
        if (hasTag(swordMaster.Enums.FlowingStance) || hasTag(swordMaster.Enums.FlowingForce)) {
            this.glowCheck();
        }
    }

    public void glowCheck() {
        this.glowColor = Master_AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (this.hasStance() || this.hasStanceForce()) {
            this.glowColor = Master_AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void receiveOnPlayerTurnStart() {

    }
}
