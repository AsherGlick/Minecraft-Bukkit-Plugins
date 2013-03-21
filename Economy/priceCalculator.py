# Constants
plotPrice = 1000
ratio_between_diamond_ore_and_coal_ore = 1 #incorrect value
ratio_between_diamond_ore_and_iron_ore = 1 # incorrect value
ratio_between_diamond_ore_and_lapis_Lazuli_ore = 1 # incorrect value
ratio_between_diamond_ore_and_gold_ore = 1 # incorrect value
ratio_between_diamond_ore_and_redstone_ore = 1 # incorrect value
ratio_between_diamond_ore_and_emerald_ore = 1 # incorrect value
ratio_between_diamond_ore_and_nether_quartz_ore = 1 #incorrect value

coal_per_ore = 5 #incorrect value
iron_per_ore = 1 #incorrect value
lapis_lazuli_per_ore = 5 #incorrect value
gold_per_ore = 1 #incorrect value
redstone_per_ore = 10 #incorrect value
diamond_per_ore = 1 #incorrect value
emerald_per_ore = 1 #incorrect value
buketlessMilk = 20 # Base Object
bucketlessWater = 20 # Base Object
bucketlessLava = 20 # Base Object
recordPrice = 200
smeltingCost = 50
genericWool = 2 # Base Item



AIR= 0 # NOTHING SELLS FOR NOTHING


# Base Items
SUGAR_CANE = 2         # Base Object
RECORD_10= recordPrice # Base Object
COCOA= 20              # Base Object
ENDER_STONE= 100 # Base Object
DIAMOND= plotPrice # Base Object
RECORD_12= recordPrice # Base Object
SLIME_BALL= 10 # Base Object
LEATHER= 4 # Base Object
WHEAT= 3 # Base Object
NETHER_STAR= 1000 # Base object
PUMPKIN = 10 # Base Object
CACTUS = 10 # Base Object
RECORD_11 = recordPrice # Base Object
LEAVES= 30 # Base Object
NETHER_BRICK= 5 # Base Object
YELLOW_FLOWER = 3 # Base Object 
SOUL_SAND= 5 # Base Item
PORK= 10 # Base Item
INK_SACK= 3 # Base Item
DIRT= 1 # Base Item
MOSSY_COBBLESTONE= 20 # Base Item
MYCEL= DIRT # Base Item
STRING= 2 # Base Item
GRAVEL= 1 # Base Item
FEATHER= 10 # Base Item
RAW_FISH= 20 # Base Item
NETHER_STALK= 10 # Base Item (Nether Wart)
ROTTEN_FLESH= 1 # Base Item
CARROT_ITEM= 1 # Base Item
POISONOUS_POTATO= 1 # Base Item
MELON_SEEDS= 5 # base item
BONE= 1 # Base item
SPONGE= 40 # Base Item
RED_ROSE= 4 # Base Item
RECORD_3= 200 # Base Item
WEB= 4 # Base Item
APPLE= 10 # Base Item
EXP_BOTTLE= 1000 # Base Item
MOB_SPAWNER= 9001 # Base Object
WATER_LILY= 2 # Base object
GLOWSTONE= 20 # Base item
CLAY_BALL= 2 # base item
FLINT= 10 # Base item
ENDER_PEARL= 50 # base item
VINE= 2 # base item
SNOW_BALL= 1 # base item
SADDLE= 6000 # base item
BLAZE_ROD= 200 # base item
MELON= 2 # base item
RED_MUSHROOM= 10 # base item
NETHERRACK= 10 # base item
CARROT= 10 # base item
POTATO_ITEM= 10 # base item
SULPHUR= 200 # BASE ITEM (GUNPOWDER)
ICE= 50 # base item
SPIDER_EYE= 1 # base item
GHAST_TEAR= 200 # base item
RAW_CHICKEN= 5 # base item
EGG= 10 # base item
OBSIDIAN= 100 # base item
SEEDS= 1 # base item
SAND= 1 # base item
BLAZE_POWDER= 150 # Base Item
BROWN_MUSHROOM= 3 # Base Item
DRAGON_EGG= 9999 # Base Item
NETHER_WARTS= 5 # base item
RAW_BEEF= 2 # base item
COBBLESTONE= 1 # Base item
SAPLING=[0]*4
SAPLING[0] = 1 # Oak Sapling  Base Item
SAPLING[1] = 1 # Spruce Sapling Base Item
SAPLING[2] = 1 # Birch Sapling Base Item
SAPLING[3] = 1 # Jungle Sapling Base Item

# Ores Objects
DIAMOND_ORE= DIAMOND * diamond_per_ore
IRON_ORE= DIAMOND_ORE * ratio_between_diamond_ore_and_iron_ore
IRON_INGOT= IRON_ORE + smeltingCost
IRON_BLOCK= (9*IRON_INGOT)
chainMailIngot = IRON_INGOT # the arbitrary price of a imaginary chainmail ingot
GOLD_ORE= (DIAMOND_ORE * ratio_between_diamond_ore_and_gold_ore)
GOLD_INGOT= GOLD_ORE + smeltingCost
REDSTONE_ORE= DIAMOND_ORE * ratio_between_diamond_ore_and_redstone_ore
REDSTONE = REDSTONE_ORE / redstone_per_ore
EMERALD_ORE= DIAMOND_ORE * ratio_between_diamond_ore_and_emerald_ore
COAL_ORE= DIAMOND_ORE * ratio_between_diamond_ore_and_coal_ore

# Wood Items
genericLog = 8
LOG=[0]*4
LOG[0] = genericLog
LOG[1] = genericLog
LOG[2] = genericLog
LOG[3] = genericLog
genericWood = genericLog / 4
WOOD = [0]*4
WOOD[0] = genericWood # Oak Wood Plank
WOOD[1] = genericWood # Spruce Wood Plank
WOOD[2] = genericWood # Brich Wood Plank
WOOD[3] = genericWood # Jungle Wood Plank
WOOD_STEP = [0]*4
WOOD_STEP[0] = (3*WOOD[0]) / 6 # Oak Wood Step
WOOD_STEP[1] = (3*WOOD[1]) / 6 # Spruce Wood Step
WOOD_STEP[2] = (3*WOOD[2]) / 6 # Birch Wood Step
WOOD_STEP[3] = (3*WOOD[3]) / 6 # Jungle Wood Step

STICK= (2*genericWood) / 4
BOWL= (3*genericWood) / 4
WOOD_PLATE= (2*genericWood)
# Stone Items
STONE= (COBBLESTONE + smeltingCost)
STONE_PLATE= (2*STONE)

# Redstone Items
REDSTONE_TORCH_ON= (REDSTONE + STICK)
DIODE = REDSTONE+(2*REDSTONE_TORCH_ON)+(3*STONE)
LEVER= COBBLESTONE + STICK

# Clay Items
CLAY_BRICK= CLAY_BALL + smeltingCost
BRICK= (4*CLAY_BRICK) 

# Buckets
BUCKET= (3*IRON_INGOT)
MILK_BUCKET = BUCKET + buketlessMilk
WATER_BUCKET= BUCKET + bucketlessWater
LAVA_BUCKET= BUCKET + bucketlessLava

# Wo0l
WOOL = [0]*16
WOOL[0] = genericWool
WOOL[1] = genericWool
WOOL[2] = genericWool
WOOL[3] = genericWool
WOOL[4] = genericWool
WOOL[5] = genericWool
WOOL[6] = genericWool
WOOL[7] = genericWool
WOOL[8] = genericWool
WOOL[9] = genericWool
WOOL[10] = genericWool
WOOL[11] = genericWool
WOOL[12] = genericWool
WOOL[13] = genericWool
WOOL[14] = genericWool
WOOL[15] = genericWool

# MISC
PAPER = (3*SUGAR_CANE) / 3
BOOK= (3*PAPER) + (LEATHER)
COMPASS= (4*IRON_INGOT) + PAPER
MAP = (8*PAPER) + COMPASS
SANDSTONE= (4*SAND)
EYE_OF_ENDER= ENDER_PEARL + BLAZE_POWDER
MINECART= (5*IRON_INGOT)

# Pistons
PISTON_BASE= (4*COBBLESTONE) + (3*genericWood) + IRON_INGOT + REDSTONE
PISTON_STICKY_BASE= PISTON_BASE + SLIME_BALL



# Item Prices
NETHER_BRICK_STAIRS= 10
BOW = (3*STICK) + (3*STRING)
DIAMOND_BLOCK = (9*DIAMOND)
CHAINMAIL_CHESTPLATE = (8*chainMailIngot)
WOOD_HOE= (2*STICK)+(2*genericWood)
POTION= 100 # Value only used for selling to the shop
NOTE_BLOCK= (8*genericWood)+(REDSTONE)
FLINT_AND_STEEL= IRON_INGOT + FLINT
GOLD_AXE= (3*GOLD_INGOT)+(2*STICK)
GLOWSTONE_DUST= (GLOWSTONE) / 4
MAGMA_CREAM= SLIME_BALL + BLAZE_POWDER
FLOWER_POT= (3*CLAY_BRICK)
MUSHROOM_SOUP= BOWL + RED_MUSHROOM + BROWN_MUSHROOM
BOOK_AND_QUILL= BOOK + INK_SACK + FEATHER
SIGN= ((6*genericWood) + STICK) / 3
SUGAR = SUGAR_CANE
RAILS = ((6*IRON_INGOT) + STICK) / 16
TNT = (4*SAND) + (5*SULPHUR)
GOLD_NUGGET = GOLD_INGOT / 9
LEATHER_HELMET = (5*LEATHER)
WOOD_STAIRS = (6*WOOD[0]) / 4 # Oak Wood Stairs
JUNGLE_WOOD_STAIRS = (6*WOOD[3]) / 4 # Jungle Wood Stairs
GOLD_PICKAXE = (3*GOLD_INGOT) + (2*STICK)
STONE_PICKAXE = (3*COBBLESTONE) + (2*STICK)
LAPIS_ORE = DIAMOND_ORE * ratio_between_diamond_ore_and_lapis_Lazuli_ore
SMOOTH_BRICK= 4*STONE
SNOW_BLOCK = 4*SNOW_BALL
STONE_SPADE = (COBBLESTONE) + (2*STICK)
GRASS = DIRT
GOLDEN_CARROT = (8*GOLD_NUGGET) + CARROT
WOOD_DOOR = WOOD * 6
GOLD_SWORD = (2*GOLD_INGOT) + (STICK)
IRON_HELMET = (5*IRON_INGOT)
BIRCH_WOOD_STAIRS= (6*WOOD[2]) / 4
COOKED_FISH= RAW_FISH + smeltingCost
LEATHER_CHESTPLATE= (8*LEATHER)
PUMPKIN_PIE= PUMPKIN + SUGAR + EGG
DIAMOND_SWORD= (2*DIAMOND) + STICK
GOLD_HELMET= (5*GOLD_INGOT)
BREWING_STAND= (3*COBBLESTONE)+BLAZE_ROD
WATCH= (4*GOLD_INGOT) + REDSTONE
CHEST= (8*genericWood)
GOLD_HOE= (2*GOLD_INGOT) + (2*STICK)
PUMPKIN_SEEDS= PUMPKIN / 4
IRON_AXE= (3*IRON_INGOT) + (2*STICK)
GOLD_SPADE= GOLD_INGOT+(2*STICK)
ENCHANTED_BOOK= 100 # Value only used for selling to the shop
TORCH= 1 # SET TO 1 because it can be made with to different objects
BRICK_STAIRS= (6*BRICK)/4
LEATHER_LEGGINGS= (7*LEATHER)
JACK_O_LANTERN= PUMPKIN + TORCH
SANDSTONE_STAIRS= (6*SANDSTONE) / 4
DIAMOND_BOOTS= (4*DIAMOND)
CHAINMAIL_BOOTS= (4*chainMailIngot)
WOOD_AXE= (3*genericWood) + (2*STICK)
SPRUCE_WOOD_STAIRS= (6*WOOD[1])/2
STONE_BUTTON= STONE
PAINTING= (8*STICK) + genericWool
IRON_FENCE=(6*IRON_INGOT)/16
GOLD_LEGGINGS= (7*GOLD_INGOT)
COBBLESTONE_STAIRS= (6*COBBLESTONE) / 4
ENDER_CHEST= (8*OBSIDIAN) + EYE_OF_ENDER
DIAMOND_PICKAXE= (3*DIAMOND) + (2*STICK)
BOAT=(5*genericWood)
IRON_BOOTS= (4*IRON_INGOT)
WORKBENCH= (4*genericWood)
SMOOTH_STAIRS= (6*SMOOTH_BRICK) / 4
CHAINMAIL_LEGGINGS= (7*chainMailIngot)
WOOD_PICKAXE= (3*genericWood) + (2*STICK)
COBBLE_WALL= (6*COBBLESTONE) / 6
DIAMOND_CHESTPLATE= (8*DIAMOND)
EMPTY_MAP= (8*PAPER) + COMPASS
IRON_LEGGINGS= (7*IRON_INGOT)
BOOKSHELF= (6*genericWood) + (3*BOOK)
TRIPWIRE_HOOK= (IRON_INGOT + STICK + genericWood) / 2
IRON_HOE= (2*IRON_INGOT) + (2*STICK)
FENCE_GATE= (4*STICK) + (2*genericWood)
ARROW= FLINT + STICK + FEATHER
CLAY= CLAY_BALL * 4
COOKIE= (2*WHEAT) + COCOA
JUKEBOX= (DIAMOND) + (8*genericWood)
REDSTONE_LAMP_OFF= (4*REDSTONE) + GLOWSTONE
STORAGE_MINECART= MINECART + CHEST
DISPENSER= (7*COBBLESTONE) + BOW + REDSTONE
CHAINMAIL_HELMET= (5*chainMailIngot)
DIAMOND_AXE= (3*DIAMOND) + (2*STICK)
COOKED_BEEF= RAW_BEEF + smeltingCost
BAKED_POTATO=  POTATO_ITEM + smeltingCost
ANVIL= (3*IRON_BLOCK) + (4*IRON_INGOT)
WOOD_BUTTON= genericWood
NETHER_FENCE= (6*NETHER_BRICK)/6
COAL= COAL_ORE / coal_per_ore
IRON_PICKAXE= (3*IRON_INGOT) + (2*STICK)
BREAD= (3*WHEAT)
FISHING_ROD= (3*STICK) + (2*STRING)
BED= (3*genericWool) + (3*genericWood)
POWERED_RAIL= ((6*GOLD_INGOT) + STICK + REDSTONE) / 6
LADDER= (7*STICK) / 3
GRILLED_PORK= PORK + smeltingCost
CAULDRON_ITEM= (7*IRON_INGOT)
SHEARS= (2*IRON_INGOT)
STONE_HOE= (2*COBBLESTONE) + (2*STICK)
DIAMOND_HOE= (2*DIAMOND) + (2*STICK)
RECORD_7= recordPrice
TRAP_DOOR= (6* genericWood) / 2
FIREBALL= (BLAZE_POWDER + COAL + SULPHUR) / 3
FENCE= (6*STICK) / 2
CAKE= (3*buketlessMilk) + (3*WHEAT) + (2*SUGAR) + EGG
IRON_CHESTPLATE= (8*IRON_INGOT)
IRON_SWORD= (2*IRON_INGOT) + STICK
GLASS= DIRT
ITEM_FRAME= (8*STICK) + LEATHER
FURNACE=(8*COBBLESTONE)
RECORD_8= recordPrice
BREWING_STAND_ITEM= (BLAZE_ROD) + (3*COBBLESTONE)
EMERALD= EMERALD_ORE / emerald_per_ore
IRON_SPADE= (IRON_INGOT) + (2*STICK)
DIAMOND_SPADE= (DIAMOND) + (2*STICK)
DIAMOND_HELMET= (5*DIAMOND)
GREEN_RECORD= recordPrice
DIODE_BLOCK_OFF= (3*STONE) + (2*REDSTONE_TORCH_ON) + REDSTONE
RECORD_6= recordPrice
GLASS_BOTTLE= (3*GLASS) / 3
POWERED_MINECART= (FURNACE + MINECART)
EMERALD_BLOCK= (9*EMERALD)
WOOD_DOUBLE_STEP = (2*WOOD_STEP)
RECORD_9= recordPrice
RECORD_4= recordPrice
THIN_GLASS= (6*GLASS) / 16
WOOD_SPADE= (genericWood) + (2*STICK)
CARROT_STICK= FISHING_ROD + CARROT_ITEM
ENCHANTMENT_TABLE= (4*OBSIDIAN) + (2*DIAMOND) + BOOK
COOKED_CHICKEN= RAW_CHICKEN + smeltingCost
GOLD_BLOCK= (9*GOLD_INGOT)
DETECTOR_RAIL= (6*IRON_INGOT) + (STONE_PLATE) + REDSTONE
SPECKLED_MELON= MELON + GOLD_NUGGET
GOLD_RECORD= recordPrice
GOLD_BOOTS= (4*GOLD_INGOT)
FERMENTED_SPIDER_EYE= SPIDER_EYE + SUGAR + BROWN_MUSHROOM
lapislazuli = LAPIS_ORE / lapis_lazuli_per_ore
LAPIS_BLOCK= 9*lapislazuli
LEATHER_BOOTS= (4*LEATHER)
WOOD_SWORD= (2*genericWood) + STICK
FLOWER_POT_ITEM= (3*CLAY_BRICK)
GOLD_CHESTPLATE= (8*GOLD_INGOT)
STONE_SWORD= (2*COBBLESTONE) + STICK
RECORD_5= recordPrice
BEACON= (5*GLASS)+ (3*OBSIDIAN) + NETHER_STAR

MELON_BLOCK= (9*MELON)
DIAMOND_LEGGINGS= (7*DIAMOND)

GOLDEN_APPLE= (8*GOLD_NUGGET) + APPLE
IRON_DOOR= (6*IRON_INGOT)
STONE_AXE= (3*COBBLESTONE) + (2*STICK)

# Half setps
STEP = [0]*7
STEP[0] = (3*STONE) / 6 # Stone Slab
STEP[1] = (3*SANDSTONE) / 6 # Sandstone Slab
STEP[2] = -1 # Wood Slab (outdated by WOOD_STEP)
STEP[3] = (3*COBBLESTONE) / 6 # Cobblestone Slab
STEP[4] = (3*BRICK) / 6 # Brick Slab
STEP[5] = (3*SMOOTH_BRICK) / 6 # Smooth Brick Slab
STEP[6] = (3*NETHER_BRICK) / 6 # Nether Brick Slab

# Invalid Items
STATIONARY_WATER= -1 # invalid item
FIRE= -1 # invalid item
FIREWORK_CHARGE= -1 #
ENDER_PORTAL_FRAME= -1 # invalid item
HUGE_MUSHROOM_2= -1 # invalid item
SOIL= -1 # Invalid item
REDSTONE_LAMP_ON= -1 # invalid item
SNOW= -1 # invalid item
COMMAND= -1 # invalid item
BURNING_FURNACE= -1 # invalid item
BEDROCK= -1 # invalid item
SKULL= -1 # in valid item
FIREWORK= -1 # dont want to figure it out
WATER= -1 # invalid block
CROPS= -1 # invalid item
TRIPWIRE= -1 # invalid item
HUGE_MUSHROOM_1= -1 # invalid item
PISTON_EXTENSION= -1 # invalid object
LAVA= -1 # invalid item
SUGAR_CANE_BLOCK= -1
SKULL_ITEM= -1 # invalid item
PUMPKIN_STEM= -1 # invalid item
ENDER_PORTAL= -1 # invalid item
SIGN_POST= -1 # invalid item
IRON_DOOR_BLOCK= -1 # invalid item
POTATO= -1 # invalid object
CAULDRON= -1 # invalid item (cultron item)
MONSTER_EGG= -1 # invalid item
PISTON_MOVING_PIECE= -1 # Invalid Object
DOUBLE_STEP= -1 # Invalid Item
MELON_STEM= -1 # Invalid Item
BED_BLOCK= -1 # Invalid Item
REDSTONE_WIRE= -1 # Invalid Item
WALL_SIGN= -1 # Invalid Item
STATIONARY_LAVA= -1 # Invalid Item
PORTAL= -1 # Invalid Object
LOCKED_CHEST= -1 # Invalid Item
CAKE_BLOCK= -1 # Invalid Item
DIODE_BLOCK_ON= -1 # Invalid Item
GLOWING_REDSTONE_ORE= -1 # Invalid Object
DEAD_BUSH= -1 # Invalid Item
WOODEN_DOOR = -1 # Invalid Object, correct object is WOOD_DOOR
LONG_GRASS= -1 # Invalad Object
WRITTEN_BOOK= -1 # Will not buy written books
MONSTER_EGGS= -1 # Value only used for selling to the shop
REDSTONE_TORCH_OFF = -1 # Invalid Object