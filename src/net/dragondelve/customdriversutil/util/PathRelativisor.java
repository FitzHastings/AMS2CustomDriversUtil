// Copyright 2023 Prokhor Kalinin
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package net.dragondelve.customdriversutil.util;

import java.io.File;
import java.nio.file.Path;

/**
 * Relativizes path to an executable path, a given path or a file.
 * If the path provided does not lie inside the user.dir or another file, path provided as an argument it returns the absolute path instead.
 */
public final class PathRelativisor {
    /**
     * The Path that is to be relativized.
     */
    Path path;

    /**
     * Default constructor. does not provide the path to be relativized.
     * If you're using this constructor you should provide the path later with setPath();
     */
    public PathRelativisor() { super();}

    /**
     *
     * @param path Path to be relativized as a Path.
     */
    public PathRelativisor(Path path) {
        this.path = path;
    }

    /**
     *
     * @param file File, a path to which you want to relativize.
     */
    public PathRelativisor(File file) {
        this.path = file.toPath();
    }

    /**
     *
     * @param pathname Pathname which you want to relativize.
     */
    public PathRelativisor(String pathname) {
        File file = new File(pathname);
        this.path = file.toPath();
    }

    /**
     * Relativizes the path to user.dir, which is the directory from which the application was launched.
     * @return Relative path of the path set in the PathRelativisor. If the path is not inside user.dir returns absolute path instead.
     */
    public String relativize() {
        return relativizeTo(System.getProperty("user.dir"));
    }

    /**
     * Relativizes the path to the pathname provided.
     * @param pathname Pathname which you want to relativize your path. This pathname has to be absolute, otherwise it will return what's already stored wether it's absolute or not.
     * @return Relative path of the path set in the PathRelativisor. If the path is not inside the file it returns absolute path instead.
     */
    public String relativizeTo(String pathname) {
        File file = new File(pathname);
        return relativizeTo(file);
    }

    /**
     * Relativizes the path to the file provided.
     * @param file File to which you want to relativize your path.
     * @return Relative path of the path set in the PathRelativisor. If the path is not inside the file it returns absolute path instead.
     */
    public String relativizeTo(File file) {
        return relativizeTo(file.toPath());
    }

    /**
     * Relativizes the path to the pathname provided.
     * @param path which you want to relativize your path. This path has to be absolute, otherwise it will return what's already stored wether it's absolute or not.
     * @return Relative path of the path set in the PathRelativisor. If the path is not inside the file it returns absolute path instead.
     */
    public String relativizeTo(Path path) {
        if(!this.path.isAbsolute())
            return this.path.toString();
        if(!path.isAbsolute())
            return this.path.toString();

        Path relative = path.relativize(this.path);
        if(relative.toString().contains(".."+File.separator))
            return this.path.toString();
        else
            return relative.toString();
    }

    /**
     * Lightweight mutator method.
     * @param path Path to be relativized as a Path.
     */
    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * Lightweight mutator method.
     * @param file File, a path to which you want to relativize;
     */
    public void setPath(File file) {
        this.path = file.toPath();
    }

    /**
     * Lightweight mutator method.
     * @param pathname Pathname which you want to relativize.
     */
    public void setPath(String pathname) {
        File file = new File(pathname);
        this.path = file.toPath();
    }
}
