Parabot-317-API-Minified
========================

A tiny 317 API for Parabot v2

### About

This API uses as few as possible hooks. This means that adding obfuscated clients requires less work now. This provider invokes the client#doAction(int) method so performing tasks is possible without any mouse/key input and thus does not require world location (tiles) to screen calculations.

### Issues
If you've an issues regarding the hooks, please report them [here](https://github.com/Parabot/Parabot-317-API-Minified/issues).

#### Maven
Parabot supports Maven as of September 2015. All information is included in the POM.xml.
The API that is supported by Parabot is also published on a Maven repository.
If you'd like to have either or both the client and the API in your project, use the following repository and dependecy tags:
```
    <repositories>
        <repository>
            <id>git-parabot</id>
            <name>Parabot's Git based repo</name>
            <url>https://maven.parabot.org/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.parabot</groupId>
            <artifactId>client</artifactId>
            <version>2.7</version>
        </dependency>
        <dependency>
            <groupId>org.parabot</groupId>
            <artifactId>317-api-minified</artifactId>
            <version>1.21.3</version>
        </dependency>
    </dependencies>
```
