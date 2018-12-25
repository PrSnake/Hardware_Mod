package fr.agh.hardware.objects.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import fr.agh.hardware.init.BlockInit;
import fr.agh.hardware.init.ItemInit;
import net.minecraft.item.ItemStack;

public class RecipesOreCrusher {
	
	private static final RecipesOreCrusher INSTANCE = new RecipesOreCrusher();
	private final Map<ItemStack, ItemStack> crushingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static RecipesOreCrusher getInstance() {
		return INSTANCE;
	}
	
	private RecipesOreCrusher()  {
		addOreCrushingRecipe(new ItemStack(BlockInit.ORE_BAUXITE), new ItemStack(ItemInit.RAW_BAUXITE), 5.0F);
		addOreCrushingRecipe(new ItemStack(BlockInit.ORE_COBALT), new ItemStack(ItemInit.RAW_COBALT), 5.0F);
		addOreCrushingRecipe(new ItemStack(BlockInit.ORE_COPPER), new ItemStack(ItemInit.RAW_COPPER), 5.0F);
		addOreCrushingRecipe(new ItemStack(BlockInit.ORE_GALENA), new ItemStack(ItemInit.RAW_GALENA), 5.0F);
		addOreCrushingRecipe(new ItemStack(BlockInit.ORE_VANADIUM), new ItemStack(ItemInit.RAW_VANADIUM), 5.0F);
	}

	
	public void addOreCrushingRecipe(ItemStack input, ItemStack result, float experience) {
		if(getOreCrushingResult(input) != ItemStack.EMPTY) return;
		this.crushingList.put(input, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getOreCrushingResult(ItemStack input) {
		for(Map.Entry<ItemStack, ItemStack> entry : this.crushingList.entrySet()) {
			if(this.compareItemStacks(input, entry.getKey())) {
				return entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem(); // && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getCrushingList() {
		return this.crushingList;
	}
	
	public float getCrushingExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if(this.compareItemStacks(stack, entry.getKey())) {
				return (entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
