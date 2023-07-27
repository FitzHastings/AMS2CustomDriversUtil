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

package net.dragondelve.mabelfx.fetcher;

/**
 * A simple interface for any class that returns an instance of T on request
 * @param <T> class of an instance to be returned by the retrieve method
 */
public interface Fetcher<T> {
    /**
     * This method is used to return an instance of T on request. It is not necessarily lightweight, don't use it as a get method.
     * @return an instance of T that can be selected from a pre generated list or newly created
     */
    T retrieve();
}
