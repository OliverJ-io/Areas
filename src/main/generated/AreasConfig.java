package io.oliverj.areas.config;

import blue.endless.jankson.Jankson;
import io.wispforest.owo.config.ConfigWrapper;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AreasConfig extends ConfigWrapper<io.oliverj.areas.config.AreasConfigModel> {

    public final Keys keys = new Keys();

    private final Option<java.lang.Boolean> enableArtifacts = this.optionForKey(this.keys.enableArtifacts);

    private AreasConfig() {
        super(io.oliverj.areas.config.AreasConfigModel.class);
    }

    private AreasConfig(Consumer<Jankson.Builder> janksonBuilder) {
        super(io.oliverj.areas.config.AreasConfigModel.class, janksonBuilder);
    }

    public static AreasConfig createAndLoad() {
        var wrapper = new AreasConfig();
        wrapper.load();
        return wrapper;
    }

    public static AreasConfig createAndLoad(Consumer<Jankson.Builder> janksonBuilder) {
        var wrapper = new AreasConfig(janksonBuilder);
        wrapper.load();
        return wrapper;
    }

    public boolean enableArtifacts() {
        return enableArtifacts.value();
    }

    public void enableArtifacts(boolean value) {
        enableArtifacts.set(value);
    }


    public static class Keys {
        public final Option.Key enableArtifacts = new Option.Key("enableArtifacts");
    }
}

