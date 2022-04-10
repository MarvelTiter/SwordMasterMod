package SwordMaster.cards;

import basemod.abstracts.CustomCard;
import basemod.interfaces.OnPlayerTurnStartSubscriber;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import SwordMaster.powers.FlowingStance;
import SwordMaster.powers.FlowingStanceForcePower;
import SwordMaster.relics.AttackCounter;
import SwordMaster.utils.StatusManage;
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
        addToBot(new ReduceFlowingStanceAction(p));
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
        boolean isGlow = false;
        if (hasTag(swordMaster.Enums.FlowingStance)) {
            isGlow |= this.hasStance();
        }
        if ( hasTag(swordMaster.Enums.FlowingForce)){
            isGlow |= this.hasStanceForce();
        }
        if (hasTag(swordMaster.Enums.HitPower)) {
            isGlow |= this.glowCheckHit();
        }
        this.glowColor = Master_AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isGlow) {
            this.glowColor = Master_AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public boolean glowCheckHit(){
        return  false;
    }

    protected AbstractGameAction CustomAttackAction(AbstractPlayer p, AbstractMonster m, AbstractGameAction.AttackEffect effect){
        DamageInfo dInfo = new DamageInfo(p, this.damage, this.damageTypeForTurn);
        return new DamageAction(m, dInfo, effect);
    }

    protected void modifyAttackCount(int amount) {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null) {
            return ;
        }
        AttackCounter counter = (AttackCounter)player.getRelic(AttackCounter.ID);
        counter.modifyCount(amount);
    }

    protected void resetAttackCounter() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null) {
            return ;
        }
        AttackCounter counter = (AttackCounter)player.getRelic(AttackCounter.ID);
        counter.reset();
    }

    @Override
    public void receiveOnPlayerTurnStart() {

    }
}
