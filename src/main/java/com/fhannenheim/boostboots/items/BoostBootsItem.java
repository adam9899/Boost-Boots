package com.fhannenheim.boostboots.items;

import com.fhannenheim.boostboots.BoostBoots;
import com.fhannenheim.boostboots.model.BootModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.enchantment.QuickChargeEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEquipable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import java.util.List;

import static com.fhannenheim.boostboots.BoostBoots.MOD_ID;

public class BoostBootsItem extends ArmorItem implements IArmorVanishable {

    public BoostBootsItem(Properties p_i48534_3_) {
        super(makeArmorMaterial(), EquipmentSlotType.FEET, p_i48534_3_);
    }
    

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.GUNPOWDER;
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlotType slot, BipedModel _default) {
        return new BootModel();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return type == null ? BoostBoots.MOD_ID + ":textures/armor/boots.png" : "";
    }

    @Nullable
    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.FEET;
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        p_77624_3_.add(new StringTextComponent("cat"));
    }


    public static IArmorMaterial makeArmorMaterial() {
        return new IArmorMaterial() {
            @Override
            public int getDurability(EquipmentSlotType slot) {
                return 0;
            }

            @Override
            public int getDamageReductionAmount(EquipmentSlotType slot) {
                return 0;
            }

            @Override
            public int getEnchantability() {
                return 0;
            }

            @Override
            public SoundEvent getSoundEvent() {
                return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
            }

            @Override
            public Ingredient getRepairMaterial() {
                return Ingredient.fromItems(Items.GUNPOWDER);
            }

            @Override
            public String getName() {
                return "boostboots:boost_boots";
            }

            @Override
            public float getToughness() {
                return 0;
            }

            @Override
            public float getKnockbackResistance() {
                return 0F;
            }
        };
    }}
