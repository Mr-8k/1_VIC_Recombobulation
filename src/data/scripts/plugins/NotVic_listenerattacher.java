package data.scripts.plugins;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseEveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.util.IntervalUtil;

import java.util.List;

public class NotVic_listenerattacher extends BaseEveryFrameCombatPlugin {

    private final IntervalUtil globalTimer=new IntervalUtil(0.05f,0.05f);

    @Override
    public void advance(float amount, List events) {

        CombatEngineAPI engine = Global.getCombatEngine();
        if (engine.isPaused()) {
            return;
        }

        globalTimer.advance(amount);
        if (globalTimer.intervalElapsed()) {

            List<WeaponAPI> weapons;
            List<ShipAPI> ships = engine.getShips();

            if (!ships.isEmpty()) {
                for (ShipAPI p : ships) {
                    if (p != null) {
                        weapons = p.getAllWeapons();
                        if (weapons != null)
                            for (WeaponAPI weapon : weapons)
                                if (weapon.getId().equals("vic_heavylaidlawaccelerator")
                                        || weapon.getId().equals("vic_lightlaidlawaccelerator")
                                        || weapon.getId().equals("vic_laidlawMassDriver"))
                                    if (!p.hasListenerOfClass(NotVic_weaponDamageListener.class))
                                        p.addListener(new NotVic_weaponDamageListener());
                    }
                }
            }
        }
    }
}