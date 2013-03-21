# 1% chance in 60 for coal          0.6
# .6% chance in 60 for iron         0.36
# .8% chance in 15 for redstone     0.12
# .1% chance 30 for gold            0.03
# .1% chance in 15 for diamond      0.015
# .1% chance in 15 for lapis        0.015

def createPrices():
    i = {}
    # Constants
    plotPrice = 1000
    ratio_between_diamond_ore_and_coal_ore = 0.015/0.6
    ratio_between_diamond_ore_and_iron_ore = 0.015/0.36
    ratio_between_diamond_ore_and_gold_ore = 0.015/0.03
    ratio_between_diamond_ore_and_lapis_ore = 0.015/0.015
    ratio_between_diamond_ore_and_emerald_ore = 0.015/0.005
    ratio_between_diamond_ore_and_redstone_ore = 0.015/0.12
    ratio_between_diamond_ore_and_nether_quartz_ore = 0.015/0.036  # incorrect value

    coal_per_ore = 1
    iron_per_ore = 1
    lapis_lazuli_per_ore = 6
    gold_per_ore = 1
    redstone_per_ore = 4.5
    diamond_per_ore = 1
    emerald_per_ore = 1
    
    buketlessMilk = 20
    bucketlessWater = 20
    bucketlessLava = 20
    recordPrice = 200
    smeltingCost = 50         # Base Price
    genericWool = 2
    AIR = 0  # NOTHING SELLS FOR NOTHING

    # Base Items
    i["SUGAR_CANE"] = 2
    i["COCOA"] = 20
    i["ENDER_STONE"] = 100
    i["DIAMOND"] = plotPrice
    i["SLIME_BALL"] = 10
    i["LEATHER"] = 4
    i["WHEAT"] = 3
    i["NETHER_STAR"] = 1000
    i["PUMPKIN"] = 10
    i["CACTUS"] = 10
    i["LEAVES"] = 30
    i["NETHER_BRICK"] = 5
    i["YELLOW_FLOWER"] = 3
    i["SOUL_SAND"] = 5
    i["PORK"] = 10
    i["INK_SACK"] = 3
    i["DIRT"] = 1
    i["MOSSY_COBBLESTONE"] = 20
    i["MYCEL"] = i["DIRT"]
    i["STRING"] = 2
    i["GRAVEL"] = 1
    i["FEATHER"] = 10
    i["RAW_FISH"] = 20
    i["NETHER_STALK"] = 10  # (Nether Wart)
    i["ROTTEN_FLESH"] = 1
    i["CARROT_ITEM"] = 1
    i["POISONOUS_POTATO"] = 1
    i["MELON_SEEDS"] = 5
    i["BONE"] = 1
    i["SPONGE"] = 40
    i["RED_ROSE"] = 4
    i["WEB"] = 4
    i["APPLE"] = 10
    i["EXP_BOTTLE"] = 1000
    i["MOB_SPAWNER"] = 9001
    i["WATER_LILY"] = 2
    i["GLOWSTONE"] = 20
    i["CLAY_BALL"] = 2
    i["FLINT"] = 10
    i["ENDER_PEARL"] = 50
    i["VINE"] = 2
    i["SNOW_BALL"] = 1
    i["SADDLE"] = 6000
    i["BLAZE_ROD"] = 200
    i["MELON"] = 2
    i["RED_MUSHROOM"] = 10
    i["NETHERRACK"] = 10
    i["CARROT"] = 10
    i["POTATO_ITEM"] = 10
    i["SULPHUR"] = 200  # (GUNPOWDER)
    i["ICE"] = 50
    i["SPIDER_EYE"] = 1
    i["GHAST_TEAR"] = 200
    i["RAW_CHICKEN"] = 5
    i["EGG"] = 10
    i["OBSIDIAN"] = 100
    i["SEEDS"] = 1
    i["SAND"] = 1
    i["BLAZE_POWDER"] = 150
    i["BROWN_MUSHROOM"] = 3
    i["DRAGON_EGG"] = 9999
    i["NETHER_WARTS"] = 5
    i["RAW_BEEF"] = 2
    i["COBBLESTONE"] = 1
    i["SAPLING"] = [0]*4  # Sapling Array
    i["SAPLING"][0] = 1   # Oak Sapling
    i["SAPLING"][1] = 1   # Spruce Sapling
    i["SAPLING"][2] = 1   # Birch Sapling
    i["SAPLING"][3] = 1   # Jungle Sapling

    # Records
    i["RECORD_3"] = recordPrice
    i["RECORD_4"] = recordPrice
    i["RECORD_5"] = recordPrice
    i["RECORD_6"] = recordPrice
    i["RECORD_7"] = recordPrice
    i["RECORD_8"] = recordPrice
    i["RECORD_9"] = recordPrice
    i["RECORD_10"] = recordPrice
    i["RECORD_11"] = recordPrice
    i["RECORD_12"] = recordPrice
    i["GOLD_RECORD"] = recordPrice
    i["GREEN_RECORD"] = recordPrice
    
    # Ores Objects
    i["DIAMOND_ORE"] = i["DIAMOND"] * diamond_per_ore
    
    i["COAL_ORE"] = i["DIAMOND_ORE"] * ratio_between_diamond_ore_and_coal_ore
    i["IRON_ORE"] = i["DIAMOND_ORE"] * ratio_between_diamond_ore_and_iron_ore
    i["GOLD_ORE"] = i["DIAMOND_ORE"] * ratio_between_diamond_ore_and_gold_ore
    i["LAPIS_ORE"] = i["DIAMOND_ORE"] * ratio_between_diamond_ore_and_lapis_ore
    i["EMERALD_ORE"] = i["DIAMOND_ORE"] * ratio_between_diamond_ore_and_emerald_ore
    i["REDSTONE_ORE"] = i["DIAMOND_ORE"] * ratio_between_diamond_ore_and_redstone_ore

    i["IRON_INGOT"] = i["IRON_ORE"] + smeltingCost
    i["IRON_BLOCK"] = (9*i["IRON_INGOT"])
    chainMailIngot = i["IRON_INGOT"]  # the price of a imaginary chainmail ingot
    i["GOLD_INGOT"] = i["GOLD_ORE"] + smeltingCost
    i["REDSTONE"] = i["REDSTONE_ORE"] / redstone_per_ore

    # Wood Items
    genericLog = 8
    i["LOG"] = [0]*4
    i["LOG"][0] = genericLog
    i["LOG"][1] = genericLog
    i["LOG"][2] = genericLog
    i["LOG"][3] = genericLog
    genericWood = genericLog / 4
    i["WOOD"] = [0]*4
    i["WOOD"][0] = genericWood  # Oak Wood Plank
    i["WOOD"][1] = genericWood  # Spruce Wood Plank
    i["WOOD"][2] = genericWood  # Brich Wood Plank
    i["WOOD"][3] = genericWood  # Jungle Wood Plank
    i["WOOD_STEP"] = [0]*4
    i["WOOD_STEP"][0] = (3*i["WOOD"][0]) / 6  # Oak Wood Step
    i["WOOD_STEP"][1] = (3*i["WOOD"][1]) / 6  # Spruce Wood Step
    i["WOOD_STEP"][2] = (3*i["WOOD"][2]) / 6  # Birch Wood Step
    i["WOOD_STEP"][3] = (3*i["WOOD"][3]) / 6  # Jungle Wood Step

    i["STICK"] = (2*genericWood) / 4
    i["BOWL"] = (3*genericWood) / 4
    i["WOOD_PLATE"] = (2*genericWood)
    # Stone Items
    i["STONE"] = (i["COBBLESTONE"] + smeltingCost)
    i["STONE_PLATE"] = (2*i["STONE"])

    # Redstone Items
    i["REDSTONE_TORCH_ON"] = (i["REDSTONE"] + i["STICK"])
    i["DIODE"] = i["REDSTONE"]+(2*i["REDSTONE_TORCH_ON"])+(3*i["STONE"])
    i["LEVER"] = i["COBBLESTONE"] + i["STICK"]

    # Clay Items
    i["CLAY_BRICK"] = i["CLAY_BALL"] + smeltingCost
    i["BRICK"] = (4*i["CLAY_BRICK"])

    # Buckets
    i["BUCKET"] = (3*i["IRON_INGOT"])
    i["MILK_BUCKET"] = i["BUCKET"] + buketlessMilk
    i["WATER_BUCKET"] = i["BUCKET"] + bucketlessWater
    i["LAVA_BUCKET"] = i["BUCKET"] + bucketlessLava

    # Wo0l
    i["WOOL"] = [0]*16
    i["WOOL"][0] = genericWool
    i["WOOL"][1] = genericWool
    i["WOOL"][2] = genericWool
    i["WOOL"][3] = genericWool
    i["WOOL"][4] = genericWool
    i["WOOL"][5] = genericWool
    i["WOOL"][6] = genericWool
    i["WOOL"][7] = genericWool
    i["WOOL"][8] = genericWool
    i["WOOL"][9] = genericWool
    i["WOOL"][10] = genericWool
    i["WOOL"][11] = genericWool
    i["WOOL"][12] = genericWool
    i["WOOL"][13] = genericWool
    i["WOOL"][14] = genericWool
    i["WOOL"][15] = genericWool

    # MISC
    i["PAPER"] = (3*i["SUGAR_CANE"]) / 3
    i["BOOK"] = (3*i["PAPER"]) + (i["LEATHER"])
    i["COMPASS"] = (4*i["IRON_INGOT"]) + i["PAPER"]
    i["MAP"] = (8*i["PAPER"]) + i["COMPASS"]
    i["SANDSTONE"] = (4*i["SAND"])
    i["EYE_OF_ENDER"] = i["ENDER_PEARL"] + i["BLAZE_POWDER"]
    i["MINECART"] = (5*i["IRON_INGOT"])

    # Pistons
    i["PISTON_BASE"] = (4*i["COBBLESTONE"]) + (3*genericWood) + i["IRON_INGOT"] + i["REDSTONE"]
    i["PISTON_STICKY_BASE"] = i["PISTON_BASE"] + i["SLIME_BALL"]

    # Picaxes
    i["GOLD_PICKAXE"] = (3*i["GOLD_INGOT"]) + (2*i["STICK"])
    i["STONE_PICKAXE"] = (3*i["COBBLESTONE"]) + (2*i["STICK"])
    i["DIAMOND_PICKAXE"] = (3*i["DIAMOND"]) + (2*i["STICK"])
    i["WOOD_PICKAXE"] = (3*genericWood) + (2*i["STICK"])
    i["IRON_PICKAXE"] = (3*i["IRON_INGOT"]) + (2*i["STICK"])

    # Item Prices
    i["NETHER_BRICK_STAIRS"] = 10
    i["BOW"] = (3*i["STICK"]) + (3*i["STRING"])
    i["DIAMOND_BLOCK"] = (9*i["DIAMOND"])
    i["CHAINMAIL_CHESTPLATE"] = (8*chainMailIngot)
    i["WOOD_HOE"] = (2*i["STICK"])+(2*genericWood)
    i["POTION"] = 100  # Value only used for selling to the shop
    i["NOTE_BLOCK"] = (8*genericWood)+(i["REDSTONE"])
    i["FLINT_AND_STEEL"] = i["IRON_INGOT"] + i["FLINT"]
    i["GOLD_AXE"] = (3*i["GOLD_INGOT"])+(2*i["STICK"])
    i["GLOWSTONE_DUST"] = (i["GLOWSTONE"]) / 4
    i["MAGMA_CREAM"] = i["SLIME_BALL"] + i["BLAZE_POWDER"]
    i["FLOWER_POT"] = (3*i["CLAY_BRICK"])
    i["MUSHROOM_SOUP"] = i["BOWL"] + i["RED_MUSHROOM"] + i["BROWN_MUSHROOM"]
    i["BOOK_AND_QUILL"] = i["BOOK"] + i["INK_SACK"] + i["FEATHER"]
    i["SIGN"] = ((6*genericWood) + i["STICK"]) / 3
    i["SUGAR"] = i["SUGAR_CANE"]
    i["RAILS"] = ((6*i["IRON_INGOT"]) + i["STICK"]) / 16
    i["TNT"] = (4*i["SAND"]) + (5*i["SULPHUR"])
    i["GOLD_NUGGET"] = i["GOLD_INGOT"] / 9
    i["LEATHER_HELMET"] = (5*i["LEATHER"])
    i["WOOD_STAIRS"] = (6*i["WOOD"][0]) / 4  # Oak Wood Stairs
    i["JUNGLE_WOOD_STAIRS"] = (6*i["WOOD"][3]) / 4  # Jungle Wood Stairs
    i["SMOOTH_BRICK"] = 4*i["STONE"]
    i["SNOW_BLOCK"] = 4*i["SNOW_BALL"]
    i["STONE_SPADE"] = (i["COBBLESTONE"]) + (2*i["STICK"])
    i["GRASS"] = i["DIRT"]
    i["GOLDEN_CARROT"] = (8*i["GOLD_NUGGET"]) + i["CARROT"]
    i["WOOD_DOOR"] = genericWood * 6
    i["GOLD_SWORD"] = (2*i["GOLD_INGOT"]) + (i["STICK"])
    i["IRON_HELMET"] = (5*i["IRON_INGOT"])
    i["BIRCH_WOOD_STAIRS"] = (6*i["WOOD"][2]) / 4
    i["COOKED_FISH"] = i["RAW_FISH"] + smeltingCost
    i["LEATHER_CHESTPLATE"] = (8*i["LEATHER"])
    i["PUMPKIN_PIE"] = i["PUMPKIN"] + i["SUGAR"] + i["EGG"]
    i["DIAMOND_SWORD"] = (2*i["DIAMOND"]) + i["STICK"]
    i["GOLD_HELMET"] = (5*i["GOLD_INGOT"])
    i["BREWING_STAND"] = (3*i["COBBLESTONE"])+i["BLAZE_ROD"]
    i["WATCH"] = (4*i["GOLD_INGOT"]) + i["REDSTONE"]
    i["CHEST"] = (8*genericWood)
    i["GOLD_HOE"] = (2*i["GOLD_INGOT"]) + (2*i["STICK"])
    i["PUMPKIN_SEEDS"] = i["PUMPKIN"] / 4
    i["IRON_AXE"] = (3*i["IRON_INGOT"]) + (2*i["STICK"])
    i["GOLD_SPADE"] = i["GOLD_INGOT"]+(2*i["STICK"])
    i["ENCHANTED_BOOK"] = 100  # Value only used for selling to the shop
    i["TORCH"] = 1  # SET TO 1 because it can be made with to different objects
    i["BRICK_STAIRS"] = (6*i["BRICK"])/4
    i["LEATHER_LEGGINGS"] = (7*i["LEATHER"])
    i["JACK_O_LANTERN"] = i["PUMPKIN"] + i["TORCH"]
    i["SANDSTONE_STAIRS"] = (6*i["SANDSTONE"]) / 4
    i["DIAMOND_BOOTS"] = (4*i["DIAMOND"])
    i["CHAINMAIL_BOOTS"] = (4*chainMailIngot)
    i["WOOD_AXE"] = (3*genericWood) + (2*i["STICK"])
    i["SPRUCE_WOOD_STAIRS"] = (6*i["WOOD"][1])/2
    i["STONE_BUTTON"] = i["STONE"]
    i["PAINTING"] = (8*i["STICK"]) + genericWool
    i["IRON_FENCE"] = (6*i["IRON_INGOT"])/16
    i["GOLD_LEGGINGS"] = (7*i["GOLD_INGOT"])
    i["COBBLESTONE_STAIRS"] = (6*i["COBBLESTONE"]) / 4
    i["ENDER_CHEST"] = (8*i["OBSIDIAN"]) + i["EYE_OF_ENDER"]
    i["BOAT"] = (5*genericWood)
    i["IRON_BOOTS"] = (4*i["IRON_INGOT"])
    i["WORKBENCH"] = (4*genericWood)
    i["SMOOTH_STAIRS"] = (6*i["SMOOTH_BRICK"]) / 4
    i["CHAINMAIL_LEGGINGS"] = (7*chainMailIngot)
    i["COBBLE_WALL"] = (6*i["COBBLESTONE"]) / 6
    i["DIAMOND_CHESTPLATE"] = (8*i["DIAMOND"])
    i["EMPTY_MAP"] = (8*i["PAPER"]) + i["COMPASS"]
    i["IRON_LEGGINGS"] = (7*i["IRON_INGOT"])
    i["BOOKSHELF"] = (6*genericWood) + (3*i["BOOK"])
    i["TRIPWIRE_HOOK"] = (i["IRON_INGOT"] + i["STICK"] + genericWood) / 2
    i["IRON_HOE"] = (2*i["IRON_INGOT"]) + (2*i["STICK"])
    i["FENCE_GATE"] = (4*i["STICK"]) + (2*genericWood)
    i["ARROW"] = i["FLINT"] + i["STICK"] + i["FEATHER"]
    i["CLAY"] = i["CLAY_BALL"] * 4
    i["COOKIE"] = (2*i["WHEAT"]) + i["COCOA"]
    i["JUKEBOX"] = (i["DIAMOND"]) + (8*genericWood)
    i["REDSTONE_LAMP_OFF"] = (4*i["REDSTONE"]) + i["GLOWSTONE"]
    i["STORAGE_MINECART"] = i["MINECART"] + i["CHEST"]
    i["DISPENSER"] = (7*i["COBBLESTONE"]) + i["BOW"] + i["REDSTONE"]
    i["CHAINMAIL_HELMET"] = (5*chainMailIngot)
    i["DIAMOND_AXE"] = (3*i["DIAMOND"]) + (2*i["STICK"])
    i["COOKED_BEEF"] = i["RAW_BEEF"] + smeltingCost
    i["BAKED_POTATO"] = i["POTATO_ITEM"] + smeltingCost
    i["ANVIL"] = (3*i["IRON_BLOCK"]) + (4*i["IRON_INGOT"])
    i["WOOD_BUTTON"] = genericWood
    i["NETHER_FENCE"] = (6*i["NETHER_BRICK"])/6
    i["COAL"] = i["COAL_ORE"] / coal_per_ore
    i["BREAD"] = (3*i["WHEAT"])
    i["FISHING_ROD"] = (3*i["STICK"]) + (2*i["STRING"])
    i["BED"] = (3*genericWool) + (3*genericWood)
    i["POWERED_RAIL"] = ((6*i["GOLD_INGOT"]) + i["STICK"] + i["REDSTONE"]) / 6
    i["LADDER"] = (7*i["STICK"]) / 3
    i["GRILLED_PORK"] = i["PORK"] + smeltingCost
    i["CAULDRON_ITEM"] = (7*i["IRON_INGOT"])
    i["SHEARS"] = (2*i["IRON_INGOT"])
    i["STONE_HOE"] = (2*i["COBBLESTONE"]) + (2*i["STICK"])
    i["DIAMOND_HOE"] = (2*i["DIAMOND"]) + (2*i["STICK"])
    i["TRAP_DOOR"] = (6*genericWood) / 2
    i["FIREBALL"] = (i["BLAZE_POWDER"] + i["COAL"] + i["SULPHUR"]) / 3
    i["FENCE"] = (6*i["STICK"]) / 2
    i["CAKE"] = (3*buketlessMilk) + (3*i["WHEAT"]) + (2*i["SUGAR"]) + i["EGG"]
    i["IRON_CHESTPLATE"] = (8*i["IRON_INGOT"])
    i["IRON_SWORD"] = (2*i["IRON_INGOT"]) + i["STICK"]
    i["GLASS"] = i["DIRT"]
    i["ITEM_FRAME"] = (8*i["STICK"]) + i["LEATHER"]
    i["FURNACE"] = (8*i["COBBLESTONE"])
    i["BEACON"] = (5*i["GLASS"]) + (3*i["OBSIDIAN"]) + i["NETHER_STAR"]
    i["BREWING_STAND_ITEM"] = (i["BLAZE_ROD"]) + (3*i["COBBLESTONE"])
    i["EMERALD"] = i["EMERALD_ORE"] / emerald_per_ore
    i["IRON_SPADE"] = (i["IRON_INGOT"]) + (2*i["STICK"])
    i["DIAMOND_SPADE"] = (i["DIAMOND"]) + (2*i["STICK"])
    i["DIAMOND_HELMET"] = (5*i["DIAMOND"])
    i["DIODE_BLOCK_OFF"] = (3*i["STONE"]) + (2*i["REDSTONE_TORCH_ON"]) + i["REDSTONE"]
    i["GLASS_BOTTLE"] = (3*i["GLASS"]) / 3
    i["POWERED_MINECART"] = (i["FURNACE"] + i["MINECART"])
    i["EMERALD_BLOCK"] = (9*i["EMERALD"])
    i["WOOD_DOUBLE_STEP"] = (2*i["WOOD_STEP"])
    i["THIN_GLASS"] = (6*i["GLASS"]) / 16
    i["WOOD_SPADE"] = (genericWood) + (2*i["STICK"])
    i["CARROT_STICK"] = i["FISHING_ROD"] + i["CARROT_ITEM"]
    i["ENCHANTMENT_TABLE"] = (4*i["OBSIDIAN"]) + (2*i["DIAMOND"]) + i["BOOK"]
    i["COOKED_CHICKEN"] = i["RAW_CHICKEN"] + smeltingCost
    i["GOLD_BLOCK"] = (9*i["GOLD_INGOT"])
    i["DETECTOR_RAIL"] = (6*i["IRON_INGOT"]) + (i["STONE_PLATE"]) + i["REDSTONE"]
    i["SPECKLED_MELON"] = i["MELON"] + i["GOLD_NUGGET"]
    i["GOLD_BOOTS"] = (4*i["GOLD_INGOT"])
    i["FERMENTED_SPIDER_EYE"] = i["SPIDER_EYE"] + i["SUGAR"] + i["BROWN_MUSHROOM"]
    lapislazuli = i["LAPIS_ORE"] / lapis_lazuli_per_ore
    i["LAPIS_BLOCK"] = 9*lapislazuli
    i["LEATHER_BOOTS"] = (4*i["LEATHER"])
    i["WOOD_SWORD"] = (2*genericWood) + i["STICK"]
    i["FLOWER_POT_ITEM"] = (3*i["CLAY_BRICK"])
    i["GOLD_CHESTPLATE"] = (8*i["GOLD_INGOT"])
    i["STONE_SWORD"] = (2*i["COBBLESTONE"]) + i["STICK"]
    i["MELON_BLOCK"] = (9*i["MELON"])
    i["DIAMOND_LEGGINGS"] = (7*i["DIAMOND"])
    i["GOLDEN_APPLE"] = (8*i["GOLD_NUGGET"]) + i["APPLE"]
    i["IRON_DOOR"] = (6*i["IRON_INGOT"])
    i["STONE_AXE"] = (3*i["COBBLESTONE"]) + (2*i["STICK"])

    # Half setps
    i["STEP"] = [0]*7
    i["STEP"][0] = (3*i["STONE"]) / 6         # Stone Slab
    i["STEP"][1] = (3*i["SANDSTONE"]) / 6     # Sandstone Slab
    i["STEP"][2] = -1                    # Wood Slab (outdated by WOOD_STEP)
    i["STEP"][3] = (3*i["COBBLESTONE"]) / 6   # Cobblestone Slab
    i["STEP"][4] = (3*i["BRICK"]) / 6         # Brick Slab
    i["STEP"][5] = (3*i["SMOOTH_BRICK"]) / 6  # Smooth Brick Slab
    i["STEP"][6] = (3*i["NETHER_BRICK"]) / 6  # Nether Brick Slab

    # Invalid Items
    i["LAVA"] = -1
    i["FIRE"] = -1
    i["SOIL"] = -1
    i["SNOW"] = -1
    i["SKULL"] = -1
    i["WATER"] = -1
    i["CROPS"] = -1
    i["POTATO"] = -1
    i["PORTAL"] = -1
    i["BEDROCK"] = -1
    i["COMMAND"] = -1
    i["TRIPWIRE"] = -1
    i["FIREWORK"] = -1
    i["CAULDRON"] = -1
    i["DEAD_BUSH"] = -1
    i["SIGN_POST"] = -1
    i["WALL_SIGN"] = -1
    i["BED_BLOCK"] = -1
    i["LONG_GRASS"] = -1
    i["SKULL_ITEM"] = -1
    i["CAKE_BLOCK"] = -1
    i["MELON_STEM"] = -1
    i["WOODEN_DOOR"] = -1
    i["MONSTER_EGG"] = -1
    i["DOUBLE_STEP"] = -1
    i["LOCKED_CHEST"] = -1
    i["PUMPKIN_STEM"] = -1
    i["ENDER_PORTAL"] = -1
    i["WRITTEN_BOOK"] = -1
    i["MONSTER_EGGS"] = -1
    i["REDSTONE_WIRE"] = -1
    i["DIODE_BLOCK_ON"] = -1
    i["FIREWORK_CHARGE"] = -1
    i["STATIONARY_LAVA"] = -1
    i["HUGE_MUSHROOM_2"] = -1
    i["HUGE_MUSHROOM_1"] = -1
    i["BURNING_FURNACE"] = -1
    i["IRON_DOOR_BLOCK"] = -1
    i["STATIONARY_WATER"] = -1
    i["REDSTONE_LAMP_ON"] = -1
    i["PISTON_EXTENSION"] = -1
    i["SUGAR_CANE_BLOCK"] = -1
    i["ENDER_PORTAL_FRAME"] = -1
    i["REDSTONE_TORCH_OFF"] = -1
    i["PISTON_MOVING_PIECE"] = -1
    i["GLOWING_REDSTONE_ORE"] = -1

    for item in i:
        print repr(item).ljust(25),
        values = [-1]*16
        if isinstance (i[item], list):
            for (index,price) in enumerate(i[item]):
                values[index] = price
        else:
            values[0] = i[item]

        for price in values:
            print repr(int(price)).rjust(5),

        print ""

createPrices();
