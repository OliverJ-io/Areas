package io.oliverj.areas.registry;

import io.oliverj.areas.item.*;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

@SuppressWarnings("unused")
public class ItemRegister implements ItemRegistryContainer {

    public static final Item CRYSTALLINE_STRUCTURE = new CrystallineStructureItem(new FabricItemSettings());
    public static final Item NULL_CRYSTAL = new NullCrystalItem(new FabricItemSettings().maxCount(1));
    public static final Item INVERSION_CRYSTAL = new InversionCrystalItem(new FabricItemSettings().maxCount(1));
    public static final Item ARTIFACT_BASE = new ArtifactBaseItem(new FabricItemSettings());
    public static final Item HURT_ITEM = new DamageTestItem(new FabricItemSettings());
}
