package com.backlogged.univercity.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.HashMap;
import java.util.Map;

public class BuildingTextureCache {

    private static final Map<String, Texture> TEXTURE_CACHE = new HashMap<>();
    private final TextureAtlas textureAtlas;
    private final float unitScale;

    public BuildingTextureCache(TextureAtlas textureAtlas, float unitScale) {
        this.textureAtlas = textureAtlas;
        this.unitScale = unitScale;
    }

    public Sprite getSprite(String atlasRegion) {

        if (TEXTURE_CACHE.containsKey(atlasRegion)) {
            return new Sprite(TEXTURE_CACHE.get(atlasRegion));
        }

        Sprite sprite = this.textureAtlas.createSprite(atlasRegion);
        sprite.setScale(this.unitScale);
        sprite.setOrigin(0, 0);

        TEXTURE_CACHE.put(atlasRegion, sprite.getTexture());

        return sprite;
    }

}
