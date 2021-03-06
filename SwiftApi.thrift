namespace java org.phybros.thrift
namespace csharp org.phybros.thrift
namespace php org.phybros.thrift

/**
 * Various codes used for catching errors
 */
enum ErrorCode {
/**
 * If a parameter was invalid
 */
	INVALID_REQUEST = 0,
/**
 * Authentication failed
 */
	INVALID_AUTHSTRING = 1,
/**
 * Requested data could not be found
 */
	NOT_FOUND = 2,
/**
 *	Something went wrong during a download operation
 */
 	DOWNLOAD_ERROR = 3,
/**
 *	Something went wrong during a file operation
 */
 	FILE_ERROR = 4,
/**
 *	Could not read a file
 */
 	NO_READ = 5,
}

/**
 * This exception is thrown when something data-related went wrong
 */
exception EDataException {
/**
 * Detailed reason for the exception
 */
	1: ErrorCode code,
/**
 * A message that describes the exception
 */
	2: string errorMessage,
}

/**
 * Thrown when authentication fails, this is thrown
 */
exception EAuthException {
/**
 * Detailed reason for the exception
 */
	1: ErrorCode code,
/**
 * A message that describes the exception
 */
	2: string errorMessage,
}

/**
 * Game difficulties
 */
enum Difficulty {
/**
 * Players regain health over time, hostile mobs don't spawn, 
 * the hunger bar does not deplete.
 */
	PEACEFUL = 0,
/**
 * Hostile mobs spawn, enemies deal less damage than on 
 * normal difficulty, the hunger bar does deplete and starving 
 * deals up to 5 hearts of damage.
 */
	EASY = 1,
/**
 * Hostile mobs spawn, enemies deal normal amounts of damage, 
 * the hunger bar does deplete and starving deals up to 9.5 
 * hearts of damage.
 */
	NORMAL = 2,
/**
 * Hostile mobs spawn, enemies deal greater damage than on 
 * normal difficulty, the hunger bar does deplete and starving 
 * can kill players.
 */
	HARD = 3,
}

/**
 * Represents various map environment types that a world may be
 */
enum Environment {
	NETHER = 0,
	NORMAL = 1,
	THE_END = 2,
}

/**
 * Valid game modes
 */
enum GameMode {
/**
 * Survival Mode
 */
	SURVIVAL = 0,
/**
 * Creative Mode
 */
	CREATIVE = 1,
/**
 * Adventure Mode
 */
	ADVENTURE = 2,
}

/**
 * All enchantments
 */
enum Enchantment {
/**
 * Protection
 */
	PROTECTION_ENVIRONMENTAL = 0,
/**
 * Fire Protection
 */
    PROTECTION_FIRE = 1,
/**
 * Feather Falling
 */
    PROTECTION_FALL = 2,
/**
 * Blast Protection
 */
    PROTECTION_EXPLOSIONS = 3,
/**
 * Projectile Protection
 */
    PROTECTION_PROJECTILE = 4,
/**
 * Respiration
 */
    OXYGEN = 5,
/**
 * Aqua Afiinity
 */
    WATER_WORKER = 6,
/**
 * Sharpness
 */
    DAMAGE_ALL = 16,
/**
 * Smite
 */
    DAMAGE_UNDEAD = 17,
/**
 * Bane of Arthropods
 */
    DAMAGE_ARTHROPODS = 18,
/**
 * Knockback
 */
    KNOCKBACK = 19,
/**
 * Fire Aspect
 */
    FIRE_ASPECT = 20,
/**
 * Looting
 */
    LOOT_BONUS_MOBS = 21,
/**
 * Efficiency
 */
    DIG_SPEED = 32,
/**
 * Silk Touch
 */
    SILK_TOUCH = 33,
/**
 * Unbreaking
 */
    DURABILITY = 34,
/**
 * Fortune
 */
    LOOT_BONUS_BLOCKS = 35,
/**
 * Power
 */
    ARROW_DAMAGE = 48,
/**
 * Punch
 */
    ARROW_KNOCKBACK = 49,
/**
 * Flame
 */
    ARROW_FIRE = 50,
/**
 * Infinity
 */
    ARROW_INFINITE = 51,
}

/**
 * A line from the console
 */
struct ConsoleLine {
	/**
	 * A unix-style timestamp (in milliseconds)
	 */
	1:i64 timestamp,
	/**
	 * The actual message from the console
	 */
	2:string message,
	/**
	 * The log level (INFO, WARN, SEVERE etc.)
	 */
	3:string level,
}

/**
 * An object that represents a location in the game world
 */
struct Location {
/**
 * The X coordinate
 */
	1: double x,
/**
 * The Y coordinate (height)
 */
	2: double y,
/**
 * The Z coordinate
 */
	3: double z,
/**
 * The pitch (vertical rotation)
 */
	4: double pitch,
/**
 * The yaw (lateral rotation)
 */
	5: double yaw,
}

/**
 * A stack of items
 */
struct ItemStack {
/**
 * How many of this item are currently in the stack
 */
	1: i32 amount,
/**
 * The type ID of the item
 */
	2: i32 typeId,
/**
 * The durability of the item
 */
	3: i32 durability,
/**
 * The current enchantments in effect on this item
 */
	4: map<Enchantment, i32> enchantments,
/**
 * The lore associated with this item
 * @since 1.5
 */
 	5: list<string> lore,
/**
 * The display name of the item
 * @since 1.5
 */
 	6: string displayName,
/**
 * The data value associated with this item
 * @since 1.5
 */
 	7: i32 data,
}

/**
 * Represents the armor that the player is wearing
 */
struct PlayerArmor {
/**
 * The item in the player's helmet armor slot
 */
	1: ItemStack helmet,
/**
 * The item in the player's chestplate armor slot
 */
	2: ItemStack chestplate,
/**
 * The item in the player's leggings armor slot
 */
	3: ItemStack leggings,
/**
 * The item in the player's boots armor slot
 */
	4: ItemStack boots,
}

/**
 * Represents a player's inventory
 */
struct PlayerInventory {
/**
 * The items in the Player's inventory
 */
	1: list<ItemStack> inventory,
/**
 * The item(s) that the player is currently holding
 */
	2: ItemStack itemInHand,
/**
 * The armor that the player is currently wearing
 */
	3: PlayerArmor armor,
}

/**
 * Represents a player on the server
 */
struct Player {
/**
 * The player's name
 */
	1: string name,
/**
 * The player's current gamemode
 */
	2: GameMode gamemode,
/**
 * Whether the player is sleeping or not
 */
	3: bool isSleeping,
/**
 * Whether the player is sneaking or not
 */
	4: bool isSneaking,
/**
 * Whether the player is sprinting or not
 */
	5: bool isSprinting,
/**
 * Whether the player is currently in a vehicle (minecart, boat, pig etc.)
 */
	6: bool isInVehicle,
/**
 * How many XP the Player needs to reach the next Level
 */
	7: i64 xpToNextLevel,
/**
 * The current Level of the player
 */
	8: i32 level,
/**
 * The IP address the player's client is currently connected on
 */
	9: string ip,
/**
 * Whether the Player is opped or not
 */
	10: bool isOp,
/**
 * How hungry the Player is in halves of chicken legs (max possible is 20)
 */
	11: i32 foodLevel,
/**
 * DEPRECATED: use Player.healthDouble instead.
 * How much health the Player has in halves of hearts (20 max)
 */
	12: i32 health,
/**
 * How tired the player is (percentage value)
 */
	13: double exhaustion,
/**
 * The time/date the the player first joined (UNIX-timestamp style)
 */
	14: i64 firstPlayed,
/**
 * The time/date the the player last joined (UNIX-timestamp style)
 */
	15: i64 lastPlayed,
/**
 * If the player is currently banned from the server
 */
	16: bool isBanned,
/**
 * If the player is currently on the server's whitelist
 */
	17: bool isWhitelisted,
/**
 * The current inventory of the player
 */
	18: PlayerInventory inventory,
/**
 * Percentage progress to the next level
 */
	19: double levelProgress,
/**
 * The port number that the player's client is currently connected on
 */
	20: i32 port,
/**
 * The current location of the player
 */
	21: Location location,	
/**
 * The health of the player. Use this instead of Player.health.
 */
	22: double healthDouble,
}

/**
 * Represents an offline player (or one that has never joined this server)
 */
struct OfflinePlayer {
/**
 * The player's name
 */
	1: string name,
/**
 * The time/date the the player first joined (UNIX-timestamp style). 0 if never.
 */
	2: i64 firstPlayed,
/**
 * The time/date the the player last joined (UNIX-timestamp style) 0 if never.
 */
	3: i64 lastPlayed,
/**
 * If the player is currently opped
 */
	4: bool isOp,
/**
 * If the player is currently banned from the server
 */
	5: bool isBanned,
/**
 * If the player is currently on the server's whitelist
 */
	6: bool isWhitelisted,
/**
 * If the player is online, more information is held in this Player object
 */
	7: Player player,
/**
 * If the player has joined the server at least once before now
 */
	8: bool hasPlayedBefore,
}

/**
 * Represents a server plugin. All the values that are populated into this 
 * object are  taken from the Plugin's server-side configuration file.
 */
struct Plugin {
/**
 * The full name of the plugin
 */
	1: string name,
/**
 * The description of the plugin
 */
	2: string description,
/**
 * The installed version of the plugin
 */
	3: string version,
/**
 * The website of the plugin
 */
	4: string website,
/**
 * The authors of the plugin
 */
	5: list<string> authors,
/**
 * Whether or not the plugin is enabled
 */
	6: bool enabled,
/**
 * The name of the JAR file that this plugin was loaded from
 */
	7: string fileName
}

/**
 * Represents a game world
 */
struct World {
/**
 * The name of the world
 */
	1: string name,
/**
 * The time of day
 */
	2: i64 time,
/**
 * Whether or not there is a storm
 */
	3: bool hasStorm,
/**
 * If there is thunder
 */
	4: bool isThundering,
/**
 * Whether or not structures are being generated
 */
	5: bool canGenerateStructures,
/**
 * Whether or not animals will spawn
 */
	6: bool allowAnimals,
/**
 * Whether or not monsters will spawn
 */
	7: bool allowMonsters,
/**
 * The difficulty of the world
 */
	8: Difficulty difficulty,
/**
 * The environment of the world
 */
	9: Environment environment,
/**
 * Gets the full in-game time on this world
 */
	10: i64 fullTime,
/**
 * The remaining time in ticks of the current conditions.
 */
	11: i64 weatherDuration,
/**
 * She Seed for this world.
 */
	12: i64 seed,
/**
 * The current PVP setting for this world.
 */
	13: bool isPvp,
/**
 * The number of currently loaded chunks
 * @since 1.7
 */
    14: i32 chunks,
/**
 * The number of entities
 * @since 1.7
 */
	15: i32 entities,
}

/**
 * Represents a piece of information regarding the health of the server.
 *
 * @since 1.7
 */
struct ServerHealth {
/**
 * The unique id for this piece of information.
 * Valid values are:
 * cpus - the number of available CPUs
 * uptime - server uptime in SECONDS
 * memMax - maximum useable amount of memory in KB
 * memTotal - the amount of memory used in KB
 * memFree - the amount of memory free in KB
 */
    1: required string id,
/**
 * The name of the piece of information. Examples would include
 * "Uptime: 154235s", "Memory Usage: 500MB"
 */
    2: required string label,
/**
 * The value of the piece of information
 */
    3: string value,
}

/**
 * Represents the game server.
 */
struct Server {
/**
 * The name of the server
 */
	1: string name,
/**
 * A list of all players who have ever played on this server.
 */
	2: list<OfflinePlayer> offlinePlayers,
/**
 * A list of players who are currently online
 */
	3: list<Player> onlinePlayers,
/**
 * The version of the server
 */
	4: string version,
/**
 * The version of CraftBukkit that is running
 */
	5: string bukkitVersion,
/**
 * The max players allowed on the server
 */
	6: i32 maxPlayers,
/**
 * The IP of the server (if set)
 */
	7: string ip,
/**
 * The port the server is listening on
 */
	8: i32 port,
/**
 * Whether flight is allowed
 */
	9: bool allowFlight,
/**
 * Whether the nether is allowed
 */
	10: bool allowNether,
/**
 * Whether the End is allowed
 */
	11: bool allowEnd,
/**
 * A list of players on the server's whitelist
 */
	12: list<OfflinePlayer> whitelist,
/**
 * A list of currently banned players
 */
	13: list<OfflinePlayer> bannedPlayers,
/**
 * A list of currently banned IP addresses
 */
	14: list<string> bannedIps,
/**
 * A list of worlds currently running on the server
 */
	15: list<World> worlds,
/**
 * Information about the server's overall health; memory usage,
 * cpu usage, uptime, chunk info etc.
 *
 * @since 1.7
 */
	16: list<ServerHealth> health,
}

service SwiftApi {
/**
 * Add an item to a player's inventory
 * 
 * @since 1.5
 *
 * @param authString
 *            The authentication hash
 * 
 * @param playerName
 *            The name of the player
 * 
 * @param item
 *            The item to add, in the form of an ItemStack
 * 
 * @return boolean true on success, false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player was not found
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool addItemToInventory(1:string authString,
							2:string playerName,
							3:ItemStack item)
	throws (1:EAuthException aex,
			2:EDataException dex),

/**
 * Add a Player to the server's whitelist. The player can be offline, or
 * be a player that has never played on this server before. If the player is
 * already on the whitelist, this method does nothing.
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the player to add to the whitelist
 * 
 * @return boolean true on success, false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player was not found
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool addToWhitelist(1:string authString,
						2:string name)
	throws (1:EAuthException aex,
			2:EDataException dex),

/**
 * Broadcasts a message to all players on the server
 *
 * @param authString
 *            The authentication hash
 *
 * @param message
 *            The message to send
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 *
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool announce(1:string authString, 2:string message)
	throws (1:EAuthException aex),

/**
 * Permanently ban a player from the server by name. The player can be
 * offline, or have never played on this server before
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the player to ban
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player was not found
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool ban(1:string authString, 
			 2:string name) 
 	throws (1:EAuthException aex, 
 			2:EDataException dex),
	
/**
 * Permanently ban a specific IP from connecting to this server
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param ip
 *            The IP address to ban
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool banIp(1:string authString, 2:string ip) throws (1:EAuthException aex),

/**
 * Copies a file into the plugins directory on the server
 *
 * @param authString
 *            The authentication hash
 * 
 * @param filename
 *			  The name of the file to move (must exist in the staging directory)
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If something went wrong during the file copy
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
/* replacing this with the "replacePlugin" method
	bool copyPlugin(1:string authString,
				    2:string fileName)
	throws (1:EAuthException aex, 
 			2:EDataException dex),
*/

/**
 * Downloads a file from the internet into the plugin's "Holding Area".
 * This method is to be used for downloading plugin files only.
 *
 * @param authString
 *            The authentication hash
 * 
 * @param url
 *            The URL of the file to be downloaded
 * 
 * @param md5
 *            The md5 hash of the file that is being downloaded
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If something went wrong during the file download,
 *				or the computed hash does not match the provided hash.
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
/* replacing this with the "replacePlugin" method
	bool downloadFile(1:string authString,
					  2:string url,
					  3:string md5)
	throws (1:EAuthException aex, 
 			2:EDataException dex),
*/

/**
 * Takes "op" (operator) privileges away from a player. If the player is
 * already deopped, then this method does nothing
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The player to deop
 * 
 * @param notifyPlayer
 *            Whether or not to tell the player that they were deopped
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the Player was not found
 * 
 * @return String the current bukkit version
 * 
 */
	bool deOp(1:string authString,
			  2:string name, 3:bool notifyPlayer) 
	throws (1:EAuthException aex, 
			2:EDataException dex),
	
/**
 * Gets the IP addresses currently banned from joining this server
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return List<String> The banned IPs
 * 
 */
	list<string> getBannedIps(1:string authString) throws (1:EAuthException aex),
	
/**
 * Gets the players currently banned from this server
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return List<OfflinePlayer> The banned players
 * 
 */
	list<OfflinePlayer> getBannedPlayers(1:string authString) throws (1:EAuthException aex),
	
/**
 * Get the current bukkit version
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return String the current bukkit version
 * 
 */
	string getBukkitVersion(1:string authString) throws (1:EAuthException aex),

/**
 * Get the last 500 console messages or console messages since a given timestamp (up to 500)
 * 
 * @param authString
 *            The authentication hash
 *
 * @param since
 *            A unix timestamp (with milliseconds). This will limit the results
 *			  to console lines that were out output after the given time
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */			
	list<ConsoleLine> getConsoleMessages(1:string authString, 2:i64 since) 
	throws (1:EAuthException aex),

/**
 * Gets the contents of a file.
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param fileName
 *            The file to get. The fileName is relative to the server
 *            root. This method cannot get the contents of any file
 *            outside the server root.
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the file could not be read or does not exist
 * 
 * @return string the contents of the file
 * 
 */
	string getFileContents(1:string authString, 2:string fileName)
	throws(1:EAuthException aex, 2:EDataException dex)
	
/**
 * Get an offline player. This method will always return an
 * OfflinePlayer object, even if the requested player has never played
 * before.
 * 
 * The "hasPlayedBefore" property can be checked to determine if the
 * requested player has ever played on this particular server before
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The player to get
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player could not be found
 * 
 * @return OfflinePlayer the requested player.
 * 
 */
	OfflinePlayer getOfflinePlayer(1:string authString, 
								   2:string name) 
	throws (1:EAuthException aex, 
			2:EDataException dex),
	
/**
 * Gets a list of all players who have ever played on this server
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return List<OfflinePlayer> A list of all players who have ever
 *         played on this server
 * 
 */
	list<OfflinePlayer> getOfflinePlayers(1:string authString) 
	throws (1:EAuthException aex),
	
/**
 * Gets a list of all players who are Opped on this server
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return List<OfflinePlayer> A list of all players who are opped
 *         on this server
 * 
 */
	list<OfflinePlayer> getOps(1:string authString) 
	throws (1:EAuthException aex),
	
/**
 * Get a player by name. Throws an EDataException if the player is
 * offline, or doesn't exist
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the player to try and get
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player is not online, or does not exist
 * 
 * @return Player The requested player. If the player could not be
 *         found, and EDataException is thrown
 * @see org.phybros.thrift.SwiftApi.Iface#getPlugins(java.lang.String)
 */
	Player getPlayer(1:string authString, 
					 2:string name) 
	throws (1:EAuthException aex, 
 			2:EDataException dex),
	
/**
 * Get all online Players
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return List<Player> A list of all currently online players
 */
	list<Player> getPlayers(1:string authString) throws (1:EAuthException aex),
	
/**
 * Get a loaded server plugin by name
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the plugin to try and get
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EDataException
 *             If the requested plugin was not found
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return Plugin The plugin
 * 
 */
	Plugin getPlugin(1:string authString, 
					 2:string name) 
	throws (1:EAuthException aex,
			2:EDataException dex),
	
/**
 * This method returns a list of all the currently loaded plugins on the
 * server.
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return List<Plugin> A list of the plugins on the server
 * 
 */
	list<Plugin> getPlugins(1:string authString) throws (1:EAuthException aex),

/**
 * Get the current server. This object contains a large amount of information
 * about the server including player and plugin information, as well as configuration
 * information.
 *
 * @param authString
 *            The authentication hash
 *
 * @throws TException
 *			  If something thrifty went wrong
 * 
 * @throws EAuthException
 *			  If the method call was not correctly authenticated
 *
 * @return Server An object containing server information
 * 
 */
 	Server getServer(1:string authString) throws (1:EAuthException aex),

/**
 * Get the current server version
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return String the version of the server
 * 
 */
	string getServerVersion(1:string authString) throws (1:EAuthException aex),

/**
 * Gets all whitelisted players
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return List<OfflinePlayer> The players on the server's whitelist
 * 
 */
	list<OfflinePlayer> getWhitelist(1:string authString) 
	throws (1:EAuthException aex),
	
/**
 * Gets a specific world by name
 * 
 * @param authString
 *            The authentication hash
 *
 * @param worldName
 *            The name of the World to get
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 *
 * @throws EDataException
 *             If the requested world could not be found
 * 
 * @return World The requested world
 * 
 */
	World getWorld(1:string authString, 2:string worldName) 
	throws (1:EAuthException aex, 2:EDataException dex),
	
/**
 * Gets all the worlds on the server
 * 
 * @param authString
 *            The authentication hash
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @return List<World> the worlds on the server
 * 
 */
	list<World> getWorlds(1:string authString) throws (1:EAuthException aex),

/**
 * This method will download and install (copy/unzip) a plugin from a given URL
 * onto the server.
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param downloadUrl
 *            The URL of the file to be downloaded
 * 
 * @param md5
 *            The md5 hash of the file that is being downloaded
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If something went wrong during the file download, or the
 *             computed hash does not match the provided hash or the
 *             requested plugin could not be found.
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool installPlugin(1:string authString, 
					   2:string downloadUrl, 
					   3:string md5) 
	throws (1:EAuthException aex, 
			2:EDataException dex),
		
/**
 * Kick a currently online Player from the server with a specific custom
 * message
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the player to kick
 * 
 * @param message
 *            The message to send to the player after they have been
 *            kicked
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player is not currently online
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool kick(1:string authString, 
			  2:string name, 
			  3:string message) 
	throws (1:EAuthException aex, 
			2:EDataException dex),
	
/**
 * Makes a player "op" (operator). If the player is already op, then
 * this method does nothing
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the player to op
 *            
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the Player was not found
 * 
 * @return String the current bukkit version
 * 
 */
	bool op(1:string authString, 
			2:string name, 3:bool notifyPlayer) 
	throws (1:EAuthException aex, 
			2:EDataException dex),

/**
 * Just a keepalive method to test authentication in clients
 *
 * @param authString
 *            The authentication hash
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool ping(1:string authString) throws (1:EAuthException aex),

/**
 * Reloads the server. This call does not send a response (for obvious reasons)
 * 
 * @param authString
 *            The authentication hash
 */
	oneway void reloadServer(1:string authString)

/**
 * Removes an item from a player's inventory
 * 
 * @since 1.5
 *
 * @param authString
 *            The authentication hash
 * 
 * @param playerName
 *            The name of the player
 * 
 * @param itemIndex
 *            The 0-based index of the item being removed
 * 
 * @return boolean true on success, false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player was not found
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool removeInventoryItem(1:string authString,
							 2:string playerName,
							 3:i32 itemIndex)
	throws (1:EAuthException aex,
			2:EDataException dex),
	
/**
 * Remove a Player from the server's whitelist. The player can be offline, or
 * be a player that has never played on this server before. If the player is not
 * already on the whitelist, this method does nothing.
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the player to remove from the whitelist
 * 
 * @return boolean true on success, false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player was not found
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool removeFromWhitelist(1:string authString,
							 2:string name)
	throws (1:EAuthException aex,
			2:EDataException dex),

/**
 * This method will replace a given plugin's .jar file with a new
 * version downloaded from the internet. The old .jar file will be moved
 * to a folder inside the SwiftApi Plugin's data folder called
 * "oldPlugins/" under the name
 * <PluginName>_<Version>-<Timestamp>.jar.old
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param pluginName
 *            The name of the plugin to replace
 * 
 * @param downloadUrl
 *            The URL of the file to be downloaded
 * 
 * @param md5
 *            The md5 hash of the file that is being downloaded
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If something went wrong during the file download, or the
 *             computed hash does not match the provided hash or the
 *             requested plugin could not be found.
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool replacePlugin(1:string authString, 
					   2:string pluginName, 
					   3:string downloadUrl, 
					   4:string md5) 
	throws (1:EAuthException aex, 
			2:EDataException dex),

/**
 * Executes a command as if you were to type it directly into the console 
 * (no need for leading forward-slash "/").
 * 
 * @param authString
 *            The authentication hash
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */	
	oneway void runConsoleCommand(1:string authString, 2:string command)
			
/**
 * Saves the specified world to disk
 *
 * @param authString
 *            The authentication hash
 *
 * @param worldName
 *            The name of the world to save
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the specified world could not be found
 *
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool saveWorld(1:string authString, 
				   2:string worldName)
	throws (1:EAuthException aex, 
			2:EDataException dex),

/**
 * Sets the contents of a file.
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param fileName
 *            The file to set. The fileName is relative to /plugins. This 
 *			  method cannot set the contents of any file outside /plugins.
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the file could not be opened or does not exist
 * 
 * @return bool true on success, else false
 * 
 */
	bool setFileContents(1:string authString, 2:string fileName, 3:string fileContents)
	throws(1:EAuthException aex, 2:EDataException dex)
				
/**
 * Sets the gamemode of a player
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the player
 * 
 * @param mode
 *            The GameMode to set the player to
 * 
 * @throws TException
 *             If something thrifty went wrong
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the Player was not found
 * 
 * @return String the current bukkit version
 * 
 */
	bool setGameMode(1:string authString, 
					 2:string name, 
					 3:GameMode mode) 
	throws (1:EAuthException aex, 
			2:EDataException dex),

/**
 * Set's the isPVP property on the specified world
 *
 * @param authString
 *            The authentication hash
 *
 * @param worldName
 *            The name of the world to set the pvp flag for
 *
 * @param isPvp
 *            The value to set the isPVP property to
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the specified world could not be found
 *
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool setPvp(1:string authString, 
			    2:string worldName, 
			    3:bool isPvp)
	throws (1:EAuthException aex, 
			2:EDataException dex),

/**
 * Set's the hasStorm property on the specified world (i.e. makes it rain)
 *
 * @param authString
 *            The authentication hash
 *
 * @param worldName
 *            The name of the world to set the storm for
 *
 * @param hasStorm
 *            The value to set the storm property to
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the specified world could not be found
 *
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool setStorm(1:string authString, 2:string worldName, 3:bool hasStorm)
	throws (1:EAuthException aex, 
			2:EDataException dex),

/**
 * Set's the isThundering property on the specified world
 *
 * @param authString
 *            The authentication hash
 *
 * @param worldName
 *            The name of the world to set the storm for
 *
 * @param isThundering
 *            The value to set the isThundering property to
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the specified world could not be found
 *
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool setThundering(1:string authString, 
					   2:string worldName, 
					   3:bool isThundering)
	throws (1:EAuthException aex, 
			2:EDataException dex),

/**
 * Sets the time on the specified world or all worlds if a 
 * blank world name is specified.
 *
 * @param authString
 *            The authentication hash
 *
 * @param worldName
 *            The name of the world to set the time for. If a blank 
 *			  world name is specified, the time is set for all worlds.
 *
 * @param time
 *            The value to set the world time
 * 
 * @return boolean true on success false on serious failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the specified world could not be found
 *
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool setWorldTime( 1:string authString, 
					   2:string worldName, 
					   3:i64 time)
	throws (1:EAuthException aex, 
			2:EDataException dex),
						
/**
 * Un ban a specific player
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param name
 *            The name of the player to unban
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player was not found
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool unBan(1:string authString, 
			   2:string name) 
	throws (1:EAuthException aex, 
			2:EDataException dex),
	
/**
 * Un ban a specific IP from connecting to this server
 * 
 * @param authString
 *            The authentication hash
 * 
 * @param ip
 *            The IP to unban
 * 
 * @return boolean true on success false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool unBanIp(1:string authString, 
				 2:string ip) 
	throws (1:EAuthException aex, 
			2:EDataException dex),

/**
 * Replaces an item in the player's inventory with the supplied one
 * 
 * @since 1.5
 *
 * @param authString
 *            The authentication hash
 * 
 * @param playerName
 *            The name of the player
 * 
 * @param item
 *            The item that will replace the item specified by itemIndex, in the form of an ItemStack
 * 
 * @param itemIndex
 *            The 0-based index of which item to replace in the inventory
 * 
 * @return boolean true on success, false on failure
 * 
 * @throws EAuthException
 *             If the method call was not correctly authenticated
 * 
 * @throws EDataException
 *             If the player was not found
 * 
 * @throws org.apache.thrift.TException
 *             If something went wrong with Thrift
 */
	bool updateInventoryItem(1:string authString,
							 2:string playerName,
							 3:ItemStack item,
							 4:i32 itemIndex)
	throws (1:EAuthException aex,
			2:EDataException dex),

}
