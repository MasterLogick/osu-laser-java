package osu.game.resources;

import java.io.InputStream;

public class ResourceManager {
    /**
     * Return resource from osu.game.resources folder as stream
     * @param name relative path to resource
     * @return InputStream from resource file
     * @see Class#getResourceAsStream(String)
     * */
    public static InputStream getResource(String name){
        return ResourceManager.class.getResourceAsStream(name);
    }
}
