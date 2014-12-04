---
---
![SwiftApi Logo](https://dev.bukkit.org/media/images/62/892/SwiftApi-256.png "SwiftApi is an Apache Thrift based API for your Bukkit server")

# SwiftApi v2.0 is going [Sponge](http://spongepowered.org)!
Because Bukkit is dead (greatly timed, exactly when SwiftApi v2.0.0 was done) v2.0.0 will be re-written for Sponge. Sponge 
is the new Minecraft modding API taking Bukkit's place. Combining the strengths of the server and client modding, i'm certain Sponge is the way forward.
  
  
I have been keeping track of Sponge's development, and they've [just released API v1.0](https://forums.spongepowered.org/t/status-update-api-release-edition/4889). 
This means i'll slowly start converting SwiftApi v2 to the Sponge platform. The same goes for Apache Thrift, they've just released v0.9.2 which allows multiplexing.
Because of extendability was decided to use in v2 even though at that time Thrift 0.9.2 was still in development. Hence the SwiftApi v2 release was stalled, and never got passed Alpha with Bukkit going awol.

Keep an eye on the repository and this page, i'll be updating it soon with release info. For now **all text below here is for the old v1.7 Bukkit version**.


Introduction
----
An Apache Thrift based API for CraftBukkit Minecraft servers. This API allows
simple calls to Bukkit methods over the internet using almost any programming
language. If you're a programmer who wants to create a killer server admin
app, this is the plugin for you!

Check out [MineCenter](http://minecenter.org) for an example of what you can do with the plugin.

Build Prerequisites
----
* Apache Maven 3.0+


Build Instructions
----
Assuming you have maven installed, just run

    mvn clean package

And maven will produce a jar file called `/target/SwiftApi-<version>.jar`

Installation
----
Just drop the jar file into your `plugins/` directory on the server, and 
restart the server.

Configuration
----
Edit the `plugins/SwiftApi/config.yml` file to taste. Some important options to 
set are:

    # Authentication Information (CHANGE THESE)
    username: admin
    password: password
    salt: saltines

Leaving these at default would make it easy for an attacker to use this API on 
your server.

Creating a Client
----
Once you have 
[downloaded the Apache Thrift compiler](http://thrift.apache.org/download/) and 
checked out this repo, you can create a client app in a few lines of code.

**1. Generate the code**  
You can generate the client library (example is in C#) by typing

    thrift -r --gen csharp SwiftApi.thrift

The `-r` option tells the compiler to generate code for all files (including 
ones that are referenced in `SwiftApi.thrift` with the `include` directive.
The `--gen csharp` option tells the compiler to generate C# code. 
This command will leave you with a gen-csharp directory with all the code you 
need to connect to a server running the SwiftApi plugin.

**2. Include the code in your app**  
If you're using C#, you can just drag all the source files right into your 
client app to use them, or alternatively you could create a new project with 
the generated files to keep your code and the generated code seperate.

**3. Call some methods!**  
Once you have included this generated code into your app, you can call API 
methods like so:

{% highlight csharp %}
TTransport transport = new TSocket("your.bukkitserver.org", 21111);
transport = new TFramedTransport(transport);

TProtocol Protocol = new TBinaryProtocol(transport, true, true);

TMultiplexedProtocol multiplex;

multiplex = new TMultiplexedProtocol(Protocol, "SwiftApi");
SwiftApi.Iface swiftApi = new SwiftApi.Client(multiplex);

// If you are using SwiftApi extensions, SwiftApiVault for example
multiplex = new TMultiplexedProtocol(Protocol, "SwiftApiVault");
SwiftApiVault.Iface swiftApiVault = new SwiftApiVault.Client(multiplex);
    
transport.Open();
Console.WriteLine("Server Version: " + swiftApi.getServerVersion(authString));
Console.WriteLine("Permission groups: " + swiftApiVault.getGroups(authString));
transport.Close();
{% endhighlight %}
    
The variable `authString` is required by all API methods. The `authString` is 
calculated like this:

{% highlight csharp %}
authString = sha256(username + methodName + password + salt);
{% endhighlight %}

Where the `username`, `password` and `salt` are the values you configured in the
plugin's config.yml file at install time and `methodName` is the name of the 
method you are calling.

Available Languages
----
As of Apache Thrift version 0.9.1, the languages you can use to talk to your server with are:

* Actionscript 3 (AS3)
* C (using GLib)
* C
* C#
* Cocoa (Objective-C on Mac OS and iOS)
* Delphi
* Erlang
* Go
* Haskell
* Java
* Javascript
* OCaml
* Perl
* PHP
* Python (including Twisted async support)
* Ruby
* Smalltalk

More Info
----
For more information on Thrift (tutorials, examples in different languages 
etc), check these sites:

* [Apache Thrift Wikipedia Page](http://en.wikipedia.org/wiki/Apache_Thrift)
* [Apache Thrift Website](http://thrift.apache.org)
* [Apache Thrift Wiki](http://wiki.apache.org/thrift/) (Recommended!)
* [The "Missing Guide" to Apache Thrift](http://diwakergupta.github.com/thrift-missing-guide/) (Recommended!)
* [A blog post I wrote about Thrift](http://willwarren.com/2012/01/24/creating-a-public-api-with-apache-thrift/)
