/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.util.local_server.services;

import com.root101.module.util.local_server.core.domain.Configuration;
import com.root101.module.util.local_server.core.module.LocalServerCoreModule;
import com.root101.module.util.local_server.core.usecase_def.LocalServerUseCase;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class LocalServerHandler {

    private static LocalServerUseCase LocalServerUC = LocalServerCoreModule.init().getImplementation(LocalServerUseCase.class);

    private LocalServerHandler() {
    }

    public static void registerLocalServerService(LocalServerUseCase newService) {
        LocalServerUC = newService;
    }

    public static void start() {
        LocalServerUC.start();
    }

    public static void close() {
        LocalServerUC.close();
    }

    public static Configuration load() throws Exception {
        return LocalServerUC.read();
    }

    public static boolean isRunning() {
        return LocalServerUC.isRunning();
    }

    public static void addPropertyChangeListener(PropertyChangeListener pl) {
        LocalServerUC.addPropertyChangeListener(pl);
    }

    public static void removePropertyChangeListener(PropertyChangeListener pl) {
        LocalServerUC.removePropertyChangeListener(pl);
    }
}
