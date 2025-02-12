# Simple Chest Gui

## Maven repository

### gradle

```groovy
repositories {
	mavenCentral()
	maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.strictpvp:ChestGui:1.0.0'
}
```

### maven

```maven
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
	<groupId>com.github.strictpvp</groupId>
    <artifactId>ChestGui</artifactId>
    <version>Tag</version>
</dependency>
```

# How to use

### 1. Register library

```java
@Override
public void onEnable() {
    ChestGui.register(this);
}
```

### 2. Init and open gui

```java
// create gui instance
ChestGui gui = new ChestGui("example title", Rows.SIX);

// add items
gui.addItem(
    <slot>,
    <ItemStack>,
    event -> {
        // left click code
        // any code which you want
    },
    event -> {
        // right click code
    },
    event -> {
        // item drop(q) code
    },
    event -> {
        // item clone(middle click) code (on creative)
    }
);

// execute chest gui
gui.execute(player);
```

+ left and right click codes

```java
gui.addItem(
    <slot>,
    <ItemStack>,
    event -> {
        // left and right click code
    }
);
```

+ when left right different

```java
gui.addItem(
    <slot>,
    <ItemStack>,
    event -> {
        // left click code
    },
    event -> {
        // right click code
    }
);
```

### Item Stack Creator
```java
ItemCreator.create(
        Material.<item>, // item
        1, // count
        "item name", // item name
        "item description\nline changing supports", // item lore
        Map<Enchantment, Integer>, // Enchantment
        ItemFlag.<flag>, // item flags
        ItemFlag...
)
```