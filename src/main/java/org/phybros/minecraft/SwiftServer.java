package org.phybros.minecraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.thrift.TException;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.phybros.thrift.ConsoleLine;
import org.phybros.thrift.EAuthException;
import org.phybros.thrift.EDataException;
import org.phybros.thrift.ErrorCode;
import org.phybros.thrift.GameMode;
import org.phybros.thrift.Player;
import org.phybros.thrift.Plugin;
import org.phybros.thrift.Server;
import org.phybros.thrift.SwiftApi;
import org.phybros.thrift.World;

public class SwiftServer {

	public class SwiftApiHandler implements SwiftApi.Iface {

		private String stagingPath = plugin.getDataFolder().getPath()
				+ "/stage";
		private String oldPluginsPath = plugin.getDataFolder().getPath()
				+ "/oldPlugins";
		private String pluginsPath = plugin.getDataFolder().getParent();

		/**
		 * Add a Player to the server's whitelist. The player can be offline, or
		 * be a player that has never played on this server before
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
		@Override
		public boolean addToWhitelist(String authString, String name)
				throws EAuthException, EDataException, TException {
			logCall("addToWhitelist");
			authenticate(authString, "addToWhitelist");

			OfflinePlayer offPl = plugin.getServer().getOfflinePlayer(name);

			if (offPl == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			try {
				boolean wasWhitelisted = offPl.isWhitelisted();
				if (!wasWhitelisted) {
					offPl.setWhitelisted(true);
					plugin.getLogger().info(
							"Whitelisted player " + offPl.getName());
				} else {
					plugin.getLogger().info(
							"Player " + offPl.getName()
									+ " is already whitelisted");
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
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
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 *
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public boolean announce(String authString, String message)
				throws EAuthException, TException {
			logCall("announce");
			authenticate(authString, "announce");

			try {
				plugin.getServer().broadcastMessage(message);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

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
		@Override
		public boolean ban(String authString, String name)
				throws EAuthException, EDataException, TException {
			logCall("ban");
			authenticate(authString, "ban");

			OfflinePlayer offPl = plugin.getServer().getOfflinePlayer(name);

			if (offPl == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			try {
				offPl.setBanned(true);
				plugin.getLogger().info("Banned player " + offPl.getName());
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/*
		 * code to be reused
		 * 
		 * @Override public boolean copyPlugin(String authString, String
		 * fileName) throws EAuthException, EDataException, TException {
		 * logCall("copyPlugin"); authenticate(authString, "copyPlugin");
		 * 
		 * File pluginToCopy = new File(stagingPath + "/" + fileName); if
		 * (!pluginToCopy.exists()) { plugin.getLogger().severe(
		 * plugin.getConfig().getString( "errorMessages.fileNotFound"));
		 * EDataException fileNotFoundEx = new EDataException();
		 * fileNotFoundEx.code = ErrorCode.FILE_ERROR;
		 * fileNotFoundEx.errorMessage = plugin.getConfig().getString(
		 * "errorMessages.fileNotFound"); throw fileNotFoundEx; }
		 * 
		 * try { String newPluginPath = pluginsPath + "/" +
		 * pluginToCopy.getName();
		 * 
		 * plugin.getLogger().info( "Copying file " + pluginToCopy + " to " +
		 * newPluginPath + "..."); FileUtils.copyFile(pluginToCopy, new
		 * File(newPluginPath)); plugin.getLogger().info("File copied."); }
		 * catch (IOException e) { plugin.getLogger().severe(e.getMessage());
		 * EDataException ioEx = new EDataException(); ioEx.code =
		 * ErrorCode.FILE_ERROR; ioEx.errorMessage = ioEx.getMessage(); throw
		 * ioEx; }
		 * 
		 * return false; }
		 */

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
		@Override
		public boolean banIp(String authString, String ip)
				throws EAuthException, TException {
			logCall("banIp");
			authenticate(authString, "banIp");

			try {
				plugin.getServer().banIP(ip);
				plugin.getLogger().info("Banned IP address " + ip);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/*
		 * code to be reused
		 * 
		 * @Override public boolean downloadFile(String authString, String url,
		 * String md5) throws EAuthException, EDataException, TException {
		 * logCall("downloadFile"); authenticate(authString, "downloadFile");
		 * 
		 * // this will create the holding area if it doesn't exist File
		 * holdingArea = new File(stagingPath);
		 * 
		 * if (!holdingArea.exists()) { plugin.getLogger().info(
		 * "Staging directory doesn't exist. Creating dir: " + stagingPath); //
		 * try and create the directory if (!holdingArea.mkdir()) {
		 * plugin.getLogger().severe( "Could not create staging directory!");
		 * EDataException e = new EDataException(); e.code =
		 * ErrorCode.FILE_ERROR; e.errorMessage = plugin.getConfig().getString(
		 * "errorMessages.createStagingError"); throw e; } }
		 * 
		 * // at this point, we can assume that "stage" exists...in theory try {
		 * plugin.getLogger().info( "Downloading file from: " + url + " ...");
		 * URL dl = new URL(url); FileUtils.copyURLToFile(dl, new
		 * File(stagingPath + "/" + FilenameUtils.getName(dl.getPath())), 5000,
		 * 60000);
		 * 
		 * File downloadedFile = new File(stagingPath + "/" +
		 * FilenameUtils.getName(dl.getPath()));
		 * plugin.getLogger().info("Download complete. Verifying md5.");
		 * 
		 * String calculatedHash = byteToString(createChecksum(downloadedFile
		 * .getPath())); plugin.getLogger().info("Calculated hash: " +
		 * calculatedHash);
		 * 
		 * if (md5.equalsIgnoreCase(calculatedHash)) {
		 * plugin.getLogger().info("Hashes match"); return true; } else {
		 * plugin.getLogger() .severe(
		 * "Downloaded file hash does not match provided hash. Deleting file.");
		 * downloadedFile.delete(); return false; } } catch
		 * (MalformedURLException e) {
		 * plugin.getLogger().severe(e.getMessage()); EDataException e1 = new
		 * EDataException(); e1.code = ErrorCode.DOWNLOAD_ERROR; e1.errorMessage
		 * = plugin.getConfig().getString( "errorMessages.malformedUrl"); throw
		 * e1; } catch (IOException e) {
		 * plugin.getLogger().severe(e.getMessage()); EDataException e1 = new
		 * EDataException(); e1.code = ErrorCode.FILE_ERROR; e1.errorMessage =
		 * e.getMessage(); throw e1; } catch (Exception e) {
		 * plugin.getLogger().severe(e.getMessage()); return false; } }
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
		@Override
		public boolean deOp(String authString, String name, boolean notifyPlayer)
				throws EAuthException, EDataException, TException {
			logCall("deOp");
			authenticate(authString, "deOp");

			OfflinePlayer offPl = plugin.getServer().getOfflinePlayer(name);

			if (offPl == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			try {
				boolean wasOp = offPl.isOp();
				offPl.setOp(false);
				if (wasOp && notifyPlayer && offPl.isOnline()) {
					offPl.getPlayer().sendMessage(
							plugin.getConfig().getString("messages.deOp"));
					plugin.getLogger().info("Deopped " + offPl.getName());
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/**
		 * Gets the IP addresses currently banned from joining this server
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @throws TException
		 *             If something thrifty went wrong
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @return List<String> The banned IPs
		 * 
		 */
		@Override
		public List<String> getBannedIps(String authString)
				throws EAuthException, TException {
			logCall("getBannedIps");
			authenticate(authString, "getBannedIps");

			List<String> bannedIps = new ArrayList<String>();

			bannedIps = new ArrayList<String>(plugin.getServer().getIPBans());
			return bannedIps;
		}

		/**
		 * Gets the players currently banned from this server
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @throws TException
		 *             If something thrifty went wrong
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @return List<OfflinePlayer> The banned players
		 * 
		 */
		@Override
		public List<org.phybros.thrift.OfflinePlayer> getBannedPlayers(
				String authString) throws EAuthException, TException {
			logCall("getBannedPlayers");
			authenticate(authString, "getBannedPlayers");

			List<org.phybros.thrift.OfflinePlayer> bannedPlayers = new ArrayList<org.phybros.thrift.OfflinePlayer>();

			List<OfflinePlayer> bukkitPlayers = new ArrayList<OfflinePlayer>(
					plugin.getServer().getBannedPlayers());

			for (OfflinePlayer bukkitPlayer : bukkitPlayers) {
				bannedPlayers.add(BukkitConverter
						.convertBukkitOfflinePlayer(bukkitPlayer));
			}

			return bannedPlayers;
		}

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
		@Override
		public String getBukkitVersion(String authString)
				throws EAuthException, TException {
			logCall("getBukkitVersion");
			authenticate(authString, "getBukkitVersion");

			return plugin.getServer().getBukkitVersion();
		}

		/**
		 * Get the last 500 console messages. This method may change in the
		 * future to include a "count" parameter so that you can specify how
		 * many lines to get, but I'm unaware how much memory it would consume
		 * to keep ALL logs (since restart or reload of plugin). Therefore it is
		 * capped at 500 for now.
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @return boolean true on success false on serious failure
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public List<ConsoleLine> getConsoleMessages(String authString)
				throws EAuthException, TException {
			// This produces some serious log spam
			// logCall("getConsoleMessages");
			authenticate(authString, "getConsoleMessages");

			return plugin.last500;
		}

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
		@Override
		public org.phybros.thrift.OfflinePlayer getOfflinePlayer(
				String authString, String name) throws EAuthException,
				EDataException, TException {
			logCall("getOfflinePlayer");
			authenticate(authString, "getOfflinePlayer");

			OfflinePlayer p = plugin.getServer().getOfflinePlayer(name);

			if (p == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.offlinePlayerNotFound"), name);
				throw e;
			}

			return BukkitConverter.convertBukkitOfflinePlayer(p);
		}

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
		@Override
		public List<org.phybros.thrift.OfflinePlayer> getOfflinePlayers(
				String authString) throws EAuthException, TException {
			logCall("getOfflinePlayers");
			authenticate(authString, "getOfflinePlayers");

			List<org.phybros.thrift.OfflinePlayer> result = new ArrayList<org.phybros.thrift.OfflinePlayer>();
			OfflinePlayer[] players = plugin.getServer().getOfflinePlayers();

			for (OfflinePlayer p : players) {
				result.add(BukkitConverter.convertBukkitOfflinePlayer(p));
			}

			return result;
		}

		/**
		 * Gets a list of all players who are Opped on this server
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @throws TException
		 *             If something thrifty went wrong
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @return List<OfflinePlayer> A list of all players who are opped on
		 *         this server
		 * 
		 */
		@Override
		public List<org.phybros.thrift.OfflinePlayer> getOps(String authString)
				throws EAuthException, TException {
			logCall("getOps");
			authenticate(authString, "getOps");

			List<org.phybros.thrift.OfflinePlayer> ops = new ArrayList<org.phybros.thrift.OfflinePlayer>();

			List<OfflinePlayer> bukkitPlayers = new ArrayList<OfflinePlayer>(
					plugin.getServer().getOperators());

			for (OfflinePlayer bukkitPlayer : bukkitPlayers) {
				ops.add(BukkitConverter
						.convertBukkitOfflinePlayer(bukkitPlayer));
			}

			return ops;
		}

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
		@Override
		public Player getPlayer(String authString, String name)
				throws EAuthException, EDataException, TException {
			logCall("getPlayer");
			authenticate(authString, "getPlayer");
			org.bukkit.entity.Player p = plugin.getServer().getPlayer(name);

			if (p == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			return BukkitConverter.convertBukkitPlayer(p);
		}

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
		@Override
		public List<Player> getPlayers(String authString)
				throws EAuthException, TException {
			logCall("getPlayers");
			authenticate(authString, "getPlayers");
			List<Player> players = new ArrayList<Player>();

			for (org.bukkit.entity.Player bPlayer : plugin.getServer()
					.getOnlinePlayers()) {
				players.add(BukkitConverter.convertBukkitPlayer(bPlayer));
			}

			return players;
		}

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
		@Override
		public Plugin getPlugin(String authString, String name)
				throws TException, EDataException, EAuthException {
			logCall("getPlugin");
			authenticate(authString, "getPlugin");

			org.bukkit.plugin.Plugin p = plugin.getServer().getPluginManager()
					.getPlugin(name);

			if (p == null) {
				plugin.getLogger().info("Plugin not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.pluginNotFound"), name);
				throw e;
			}

			return BukkitConverter.convertBukkitPlugin(p);
		}

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
		@Override
		public List<Plugin> getPlugins(String authString) throws TException,
				EAuthException {
			logCall("getPlugins");
			authenticate(authString, "getPlugins");

			List<Plugin> serverPlugins = new ArrayList<Plugin>();

			for (org.bukkit.plugin.Plugin p : plugin.getServer()
					.getPluginManager().getPlugins()) {

				serverPlugins.add(BukkitConverter.convertBukkitPlugin(p));
			}

			return serverPlugins;
		}

		/**
		 * Get the current server. This object contains a large amount of
		 * information about the server including player and plugin information,
		 * as well as configuration information.
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @throws TException
		 *             If something thrifty went wrong
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @return Server An object containing server information
		 * 
		 */
		@Override
		public Server getServer(String authString) throws EAuthException,
				TException {
			logCall("getServer");
			authenticate(authString, "getServer");

			Server s = new Server();
			org.bukkit.Server server = plugin.getServer();

			s.allowEnd = server.getAllowEnd();
			s.allowFlight = server.getAllowFlight();
			s.allowNether = server.getAllowNether();
			s.bannedIps = new ArrayList<String>(server.getIPBans());

			s.bannedPlayers = new ArrayList<org.phybros.thrift.OfflinePlayer>();
			for (OfflinePlayer op : server.getBannedPlayers()) {
				s.bannedPlayers.add(BukkitConverter
						.convertBukkitOfflinePlayer(op));
			}

			s.bukkitVersion = server.getBukkitVersion();
			s.ip = server.getIp();
			s.maxPlayers = server.getMaxPlayers();
			s.name = server.getServerName();
			s.offlinePlayers = new ArrayList<org.phybros.thrift.OfflinePlayer>();

			for (OfflinePlayer op : server.getOfflinePlayers()) {
				s.offlinePlayers.add(BukkitConverter
						.convertBukkitOfflinePlayer(op));
			}

			s.onlinePlayers = new ArrayList<Player>();

			for (org.bukkit.entity.Player p : server.getOnlinePlayers()) {
				s.onlinePlayers.add(BukkitConverter.convertBukkitPlayer(p));
			}

			s.port = server.getPort();
			s.version = server.getVersion();

			s.whitelist = new ArrayList<org.phybros.thrift.OfflinePlayer>();

			for (OfflinePlayer op : server.getWhitelistedPlayers()) {
				s.whitelist.add(BukkitConverter.convertBukkitOfflinePlayer(op));
			}

			s.worlds = new ArrayList<World>();

			for (org.bukkit.World w : server.getWorlds()) {
				s.worlds.add(BukkitConverter.convertBukkitWorld(w));
			}

			return s;
		}

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
		@Override
		public String getServerVersion(String authString)
				throws EAuthException, TException {
			logCall("getServerVersion");
			authenticate(authString, "getServerVersion");

			return plugin.getServer().getVersion();
		}

		/**
		 * Gets all whitelisted players
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @throws TException
		 *             If something thrifty went wrong
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @return List<OfflinePlayer> The players on the server's whitelist
		 * 
		 */
		@Override
		public List<org.phybros.thrift.OfflinePlayer> getWhitelist(
				String authString) throws EAuthException, TException {
			logCall("getWhitelist");
			authenticate(authString, "getWhitelist");

			List<org.phybros.thrift.OfflinePlayer> whitelist = new ArrayList<org.phybros.thrift.OfflinePlayer>();

			List<OfflinePlayer> bukkitPlayers = new ArrayList<OfflinePlayer>(
					plugin.getServer().getWhitelistedPlayers());

			for (OfflinePlayer bukkitPlayer : bukkitPlayers) {
				whitelist.add(BukkitConverter
						.convertBukkitOfflinePlayer(bukkitPlayer));
			}

			return whitelist;
		}

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
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws Errors.EDataException
		 *             If the requested world could not be found
		 * 
		 * @return World The requested world
		 * 
		 */
		@Override
		public World getWorld(String authString, String worldName)
				throws EAuthException, EDataException, TException {
			logCall("getWorld");
			authenticate(authString, "getWorld");

			org.bukkit.World w = plugin.getServer().getWorld(worldName);

			if (w == null) {
				plugin.getLogger().info("World not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.worldNotFound"), worldName);
				throw e;
			}

			return BukkitConverter.convertBukkitWorld(w);
		}

		/**
		 * Gets all the worlds on the server
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @throws TException
		 *             If something thrifty went wrong
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @return List<World> the worlds on the server
		 * 
		 */
		@Override
		public List<World> getWorlds(String authString) throws EAuthException,
				TException {
			logCall("getWorlds");
			authenticate(authString, "getWorlds");
			List<World> worlds = new ArrayList<World>();

			for (org.bukkit.World w : plugin.getServer().getWorlds()) {
				worlds.add(BukkitConverter.convertBukkitWorld(w));
			}

			return worlds;
		}

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
		@Override
		public boolean kick(String authString, String name, String message)
				throws EAuthException, EDataException, TException {
			logCall("kick");
			authenticate(authString, "kick");

			org.bukkit.entity.Player player = plugin.getServer()
					.getPlayer(name);

			if (player == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			try {
				player.kickPlayer(message);
				plugin.getLogger().info(
						"Kicked " + player.getName() + " with message \""
								+ message + "\"");
				return true;
			} catch (Exception e) {
				return false;
			}

		}

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
		@Override
		public boolean op(String authString, String name, boolean notifyPlayer)
				throws EAuthException, EDataException, TException {
			logCall("op");
			authenticate(authString, "op");

			OfflinePlayer offPl = plugin.getServer().getOfflinePlayer(name);

			if (offPl == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			try {
				boolean wasOp = offPl.isOp();
				offPl.setOp(true);
				if (!wasOp && notifyPlayer && offPl.isOnline()) {
					offPl.getPlayer().sendMessage(
							plugin.getConfig().getString("messages.op"));
					plugin.getLogger().info("Opped " + offPl.getName());
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/**
		 * Just a keepalive method to test authentication in clients
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @return boolean true on success false on serious failure
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public boolean ping(String authString) throws EAuthException,
				TException {
			logCall("ping");
			authenticate(authString, "ping");

			return true;
		}

		/**
		 * Reloads the server. This call does not send a response (for obvious
		 * reasons)
		 * 
		 * @param authString
		 *            The authentication hash
		 */
		@Override
		public void reloadServer(String authString) {
			logCall("reloadServer");

			try {
				authenticate(authString, "reloadServer");
				plugin.getServer().reload();
			} catch (Exception e) {
				plugin.getLogger().info(
						"Error while reloading: " + e.getMessage());
			}

		}

		/**
		 * Remove a Player from the server's whitelist. The player can be
		 * offline, or be a player that has never played on this server before
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @param name
		 *            The name of the player to remove from the whitelist
		 * 
		 * @return boolean true on success, false on failure
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws Errors.EDataException
		 *             If the player was not found
		 * 
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public boolean removeFromWhitelist(String authString, String name)
				throws EAuthException, EDataException, TException {
			logCall("removeFromWhitelist");
			authenticate(authString, "removeFromWhitelist");

			OfflinePlayer offPl = plugin.getServer().getOfflinePlayer(name);

			if (offPl == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			try {
				boolean wasWhitelisted = offPl.isWhitelisted();
				if (wasWhitelisted) {
					offPl.setWhitelisted(false);
					plugin.getLogger().info(
							"Removed player " + offPl.getName()
									+ " from the whitelist");
				} else {
					plugin.getLogger().info(
							"Player " + offPl.getName()
									+ " is not on the whitelist");
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}

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
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws Errors.EDataException
		 *             If something went wrong during the file download, or the
		 *             computed hash does not match the provided hash or the
		 *             requested plugin could not be found.
		 * 
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public boolean replacePlugin(String authString, String pluginName,
				String downloadUrl, String md5) throws EAuthException,
				EDataException, TException {
			logCall("replacePlugin");
			authenticate(authString, "replacePlugin");

			// get the plugin
			org.bukkit.plugin.Plugin p = plugin.getServer().getPluginManager()
					.getPlugin(pluginName);

			// throw an EDataException if the plugin was not found
			if (p == null) {
				plugin.getLogger().info("Plugin not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.pluginNotFound"), pluginName);
				throw e;
			}

			// this will create the holding area if it doesn't exist
			File holdingArea = new File(stagingPath);

			// if the staging directory doesn't exist, then create it
			if (!holdingArea.exists()) {
				plugin.getLogger().info(
						"Staging directory doesn't exist. Creating dir: "
								+ stagingPath);
				// try and create the directory
				if (!holdingArea.mkdir()) {
					plugin.getLogger().severe(
							"Could not create staging directory!");
					EDataException e = new EDataException();
					e.code = ErrorCode.FILE_ERROR;
					e.errorMessage = plugin.getConfig().getString(
							"errorMessages.createDirectoryError");
					throw e;
				}
			}

			// at this point, we can assume that "stage" exists...in theory

			plugin.getLogger().info(
					"Downloading file from: " + downloadUrl + " ...");

			try {
				URL dl = new URL(downloadUrl);

				// create a locatio to download the file to
				File downloadedFileObject = new File(stagingPath + "/"
						+ FilenameUtils.getName(dl.getPath()));

				// download the file
				FileUtils.copyURLToFile(dl, downloadedFileObject, 5000, 60000);

				File downloadedFile = new File(stagingPath + "/"
						+ FilenameUtils.getName(dl.getPath()));
				plugin.getLogger().info("Download complete. Verifying md5.");

				// figure out the MD5 hash of the downloaded file
				String calculatedHash = byteToString(createChecksum(downloadedFile
						.getPath()));
				plugin.getLogger().info("Calculated hash: " + calculatedHash);

				if (md5.equalsIgnoreCase(calculatedHash)) {
					plugin.getLogger().info("Hashes match");
				} else {
					plugin.getLogger()
							.severe("Downloaded file hash does not match provided hash. Deleting file.");
					downloadedFile.delete();

					EDataException e = new EDataException();
					e.code = ErrorCode.DOWNLOAD_ERROR;
					e.errorMessage = String.format(plugin.getConfig()
							.getString("errorMessages.hashMismatch"),
							pluginName);
					throw e;
				}

				// get the plugin's JAR file name
				String jarFileName = "";

				// this returns the "classes/" directory when debugging
				ProtectionDomain pd = p.getClass().getProtectionDomain();
				File f = new File(pd.getCodeSource().getLocation().toString());
				plugin.getLogger().info(
						"Finding original JAR file..." + f.getPath());
				if (f.isDirectory()) {
					EDataException e = new EDataException();
					e.code = ErrorCode.FILE_ERROR;
					e.errorMessage = String
							.format(plugin.getConfig().getString(
									"errorMessages.jarNotFound"), pluginName);
					throw e;
				} else {
					jarFileName = f.getName();
					plugin.getLogger().info(
							"Located original JAR file: " + f.getName());
				}

				// move the current jarfile to the oldPlugins directory

				// this will create the oldPlugins area if it doesn't exist
				File oldPlugins = new File(oldPluginsPath);

				// if the staging directory doesn't exist, then create it
				if (!oldPlugins.exists()) {
					plugin.getLogger().info(
							"Old Plugins directory doesn't exist. Creating dir: "
									+ oldPluginsPath);
					// try and create the directory
					if (!oldPlugins.mkdir()) {
						plugin.getLogger().severe(
								"Could not create Old Plugins directory!");
						EDataException e = new EDataException();
						e.code = ErrorCode.FILE_ERROR;
						e.errorMessage = plugin.getConfig().getString(
								"errorMessages.createDirectoryError");
						throw e;
					}
				}

				// copy the plugin to the backup directory
				// Format: <PluginName>_<Version>-<Timestamp>.jar.old
				String destination = oldPluginsPath
						+ "/"
						+ (p.getName() + "_" + p.getDescription().getVersion()
								+ "-" + String.valueOf(System
								.currentTimeMillis())).replaceAll("\\W+", "-")
						+ ".jar.old";

				plugin.getLogger().info(
						"Backing up old JAR file to " + destination);
				File oldPlugin = new File(pluginsPath + "/" + f.getName());
				FileUtils.copyFile(oldPlugin, new File(destination), false);

				if (downloadedFile.getName().compareTo(f.getName()) != 0) {
					plugin.getLogger().warning(
							"The new jar file for plugin " + p.getName()
									+ " will still be named " + f.getName());
				}

				plugin.getLogger().info("Installing new JAR file..");
				// copy the downloaded file to the plugins DIR
				String newDownloadedPluginPath = pluginsPath + "/"
						+ downloadedFile.getName();
				if (downloadedFileObject.getName().endsWith(".jar")) {
					FileUtils.copyFile(downloadedFileObject, oldPlugin);

					plugin.getLogger().info(
							"Plugin installation complete. Reload or restart to use new version.");
				} else {
					plugin.getLogger()
							.warning("Sorry, SwiftApi can only install plugins with the extension \".jar\"");
					EDataException e = new EDataException();
					e.code = ErrorCode.FILE_ERROR;
					e.errorMessage = plugin.getConfig().getString(
							"errorMessages.invalidPluginType");
					throw e;
				}
				return true;
			} catch (MalformedURLException e) {
				plugin.getLogger().severe(e.getMessage());
				EDataException e1 = new EDataException();
				e1.code = ErrorCode.DOWNLOAD_ERROR;
				e1.errorMessage = plugin.getConfig().getString(
						"errorMessages.malformedUrl");
				throw e1;
			} catch (FileNotFoundException e) {
				plugin.getLogger().severe(e.getMessage());
				EDataException e1 = new EDataException();
				e1.code = ErrorCode.FILE_ERROR;
				e1.errorMessage = e.getMessage();
				throw e1;
			} catch (IOException e) {
				plugin.getLogger().severe(e.getMessage());
				EDataException e1 = new EDataException();
				e1.code = ErrorCode.FILE_ERROR;
				e1.errorMessage = e.getMessage();
				throw e1;
			} catch (NoSuchAlgorithmException e) {
				plugin.getLogger().severe(e.getMessage());
				EDataException e1 = new EDataException();
				e1.code = ErrorCode.FILE_ERROR;
				e1.errorMessage = e.getMessage();
				throw e1;
			}
		}

		/**
		 * Executes a command as if you were to type it directly into the
		 * console (no need for leading forward-slash "/").
		 * 
		 * @param authString
		 *            The authentication hash
		 * 
		 * @return boolean true on success false on serious failure
		 * 
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public void runConsoleCommand(String authString, String command) {
			logCall("runConsoleCommand");

			try {
				authenticate(authString, "runConsoleCommand");
				plugin.getServer().dispatchCommand(
						plugin.getServer().getConsoleSender(), command);
			} catch (Exception e) {
				plugin.getLogger().severe(e.getMessage());
			}
		}

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
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws Errors.EDataException
		 *             If the specified world could not be found
		 *
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public boolean saveWorld(String authString, String worldName)
				throws EAuthException, EDataException, TException {
			org.bukkit.World w = plugin.getServer().getWorld(worldName);

			if (w == null) {
				plugin.getLogger().info("World not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.worldNotFound"), worldName);
				throw e;
			}
			
			try {
				plugin.getLogger().info("Saving world \"" + worldName + "\"...");
				w.save();
				plugin.getLogger().info("World saved.");
				return true;				
			} catch (Exception e) {
				return false;
			}			
		}

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
		@Override
		public boolean setGameMode(String authString, String name, GameMode mode)
				throws EAuthException, EDataException, TException {
			logCall("setGameMode");
			authenticate(authString, "setGameMode");

			org.bukkit.entity.Player player = plugin.getServer()
					.getPlayer(name);

			if (player == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			try {
				org.bukkit.GameMode m = org.bukkit.GameMode.getByValue(mode
						.getValue());
				player.setGameMode(m);
				plugin.getLogger().info(
						"Set gamemode to " + String.valueOf(m.getValue())
								+ " for " + player.getName());
				return true;
			} catch (Exception e) {
				return false;
			}
		}

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
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws Errors.EDataException
		 *             If the specified world could not be found
		 *
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public boolean setPvp(String authString, String worldName, boolean isPvp)
				throws EAuthException, EDataException, TException {
			org.bukkit.World w = plugin.getServer().getWorld(worldName);

			if (w == null) {
				plugin.getLogger().info("World not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.worldNotFound"), worldName);
				throw e;
			}
			
			try {
				plugin.getLogger().info("Setting PVP to " + String.valueOf(isPvp) + " for world \"" + worldName + "\"");
				w.setPVP(isPvp);
				return true;				
			} catch (Exception e) {
				return false;
			}	
		}

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
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws Errors.EDataException
		 *             If the specified world could not be found
		 *
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */		
		@Override
		public boolean setStorm(String authString, String worldName,
				boolean hasStorm) throws EAuthException, EDataException,
				TException {
			org.bukkit.World w = plugin.getServer().getWorld(worldName);

			if (w == null) {
				plugin.getLogger().info("World not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.worldNotFound"), worldName);
				throw e;
			}
			
			try {
				plugin.getLogger().info("Setting storm to " + String.valueOf(hasStorm) + " for world \"" + worldName + "\"");
				w.setStorm(hasStorm);
				return true;				
			} catch (Exception e) {
				return false;
			}	
		}

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
		 * @throws Errors.EAuthException
		 *             If the method call was not correctly authenticated
		 * 
		 * @throws Errors.EDataException
		 *             If the specified world could not be found
		 *
		 * @throws org.apache.thrift.TException
		 *             If something went wrong with Thrift
		 */
		@Override
		public boolean setThundering(String authString, String worldName,
				boolean isThundering) throws EAuthException, EDataException,
				TException {
			org.bukkit.World w = plugin.getServer().getWorld(worldName);

			if (w == null) {
				plugin.getLogger().info("World not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.worldNotFound"), worldName);
				throw e;
			}
			
			try {
				plugin.getLogger().info("Setting thundering to " + String.valueOf(isThundering) + " for world \"" + worldName + "\"");
				w.setThundering(isThundering);
				return true;				
			} catch (Exception e) {
				return false;
			}	
		}

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
		@Override
		public boolean unBan(String authString, String name)
				throws EAuthException, EDataException, TException {
			logCall("unBan");
			authenticate(authString, "unBan");
			OfflinePlayer offPl = plugin.getServer().getOfflinePlayer(name);

			if (offPl == null) {
				plugin.getLogger().info("Player not found");
				EDataException e = new EDataException();
				e.code = ErrorCode.NOT_FOUND;
				e.errorMessage = String.format(
						plugin.getConfig().getString(
								"errorMessages.playerNotFound"), name);
				throw e;
			}

			try {
				offPl.setBanned(false);
				plugin.getLogger().info("Un banned player " + offPl.getName());
				return true;
			} catch (Exception e) {
				return false;
			}
		}

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
		@Override
		public boolean unBanIp(String authString, String ip)
				throws EAuthException, EDataException, TException {
			logCall("unBanIp");
			authenticate(authString, "unBanIp");

			try {
				plugin.getServer().unbanIP(ip);
				plugin.getLogger().info("Un banned IP address " + ip);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/**
		 * Authenticate a method call.
		 * 
		 * @param authString
		 *            The authentication hash
		 * @param methodName
		 *            The method that is being called.
		 * @throws EAuthException
		 *             This is thrown if the authString is invalid.
		 */
		private void authenticate(String authString, String methodName)
				throws EAuthException {
			String username = plugin.getConfig().getString("username");
			String password = plugin.getConfig().getString("password");
			String salt = plugin.getConfig().getString("salt");

			// build the pre-hashed string
			String myAuthString = username + methodName + password + salt;
			MessageDigest md = null;

			try {
				md = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException algex) {
				plugin.getLogger().severe(algex.getMessage());
			}
			md.update(myAuthString.getBytes());
			String hash = byteToString(md.digest());
			// plugin.getLogger().info("Expecting: " + hash);
			// plugin.getLogger().info("Received:  " + authString);

			if (!hash.equalsIgnoreCase(authString)) {
				plugin.getLogger().info(
						String.format(
								"Invalid Authentication received (method: %s)",
								methodName));
				EAuthException e = new EAuthException();
				e.code = ErrorCode.INVALID_AUTHSTRING;
				e.errorMessage = plugin.getConfig().getString(
						"errorMessages.invalidAuthentication");
				plugin.getLogger().info(e.toString());
				throw e;
			}
		}

		private String byteToString(byte[] bytes) {
			String result = "";
			for (int i = 0; i < bytes.length; i++) {
				result += String.format("%02x", bytes[i]);
			}
			return result;
		}

		private byte[] createChecksum(String filename)
				throws NoSuchAlgorithmException, FileNotFoundException,
				IOException {
			InputStream fis = new FileInputStream(filename);

			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance("MD5");
			int numRead;
			do {
				numRead = fis.read(buffer);
				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
			fis.close();
			return complete.digest();
		}

		/**
		 * Log an API call. If the config option logMethodCalls is false, this
		 * method does nothing.
		 * 
		 * @param methodName
		 *            Name of the method that was called.
		 */
		private void logCall(String methodName) {
			if (plugin.getConfig().getBoolean("logMethodCalls")) {
				plugin.getLogger().info(
						"SwiftApi method called: " + methodName + "()");
			}
		}

	}

	private int port;

	private TServer server;
	private SwiftApiPlugin plugin;

	public SwiftServer(SwiftApiPlugin plugin) throws InterruptedException {
		this.plugin = plugin;
		this.port = plugin.getConfig().getInt("port");

		this.start();
	}

	public void stop() {
		try {
			plugin.getLogger().info("Stopping server...");
			server.stop();
			plugin.getLogger().info("Server stopped successfully");
		} catch (Exception e) {
			plugin.getLogger().severe(
					"Error while stopping server: " + e.getMessage());
		}
	}

	private void start() {
		(new Thread(new Runnable() {

			public void run() {
				try {

					plugin.getLogger().info(
							"Sleeping for 2 seconds before starting up...");
					Thread.sleep(2000);

					SwiftApiHandler psh = new SwiftApiHandler();
					SwiftApi.Processor<SwiftApi.Iface> pro = new SwiftApi.Processor<SwiftApi.Iface>(
							psh);

					/*
					 * TNonblockingServerTransport tst = new
					 * TNonblockingServerSocket( port); server = new
					 * TNonblockingServer( new
					 * TNonblockingServer.Args(tst).processor(pro));
					 * plugin.getLogger().info( "Listening on port " +
					 * String.valueOf(port)); server.serve();
					 */
					 
					/*
					TServerTransport tst = new TServerSocket(port);
					server = new TSimpleServer(
							new TSimpleServer.Args(tst).processor(pro));
					plugin.getLogger().info(
							"Listening on port " + String.valueOf(port));
					*/
					
					TNonblockingServerTransport tst = new TNonblockingServerSocket(port);
					server = new TNonblockingServer(new TNonblockingServer.Args(tst).processor(pro));
					plugin.getLogger().info(
							"Listening on port " + String.valueOf(port));
					server.serve();
				} catch (Exception e) {
					plugin.getLogger().severe(e.getMessage());
				}
			}

		})).start();

	}

}
