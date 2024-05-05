package io.oliverj.areas.registry;

import io.oliverj.areas.item.ArtifactBaseItem;
import io.oliverj.areas.item.ArtifactOfResurrectionItem;
import io.oliverj.areas.item.CrystallineStructureItem;
import io.oliverj.areas.item.GUITestItem;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class ArtifactRegister implements ItemRegistryContainer {

    public static final Item RESURRECT_ARTIFACT = new ArtifactOfResurrectionItem(new FabricItemSettings());
}
