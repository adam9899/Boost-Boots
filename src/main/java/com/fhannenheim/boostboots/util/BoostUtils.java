package com.fhannenheim.boostboots.util;

import com.fhannenheim.boostboots.compat.CuriosCompat;
import com.fhannenheim.boostboots.init.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;

public class BoostUtils {

    public static boolean hasBoostBoots(PlayerEntity player) {
        return player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == Items.BOOST_BOOTS.get()
                || (isCuriosLoaded() && CuriosCompat.getBoostCurio(player).isPresent());
    }

    public static boolean isCuriosLoaded() {
        return ModList.get() != null && ModList.get().getModContainerById("curios").isPresent();
    }

    public static List<ItemStack> getEquippedBoots(PlayerEntity player) {
        List<ItemStack> boots = new ArrayList<>();
        if (isCuriosLoaded() && CuriosCompat.getBoostCurio(player).isPresent()) {
            boots.add(CuriosCompat.getBoostCurio(player).get().right);
        }
        if (player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == Items.BOOST_BOOTS.get())
            boots.add(player.getItemStackFromSlot(EquipmentSlotType.FEET));
        return boots;
    }
}
