/*******************************************************************************
 * Copyright 2012 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/*
 * Copyright 2012 Diego Martin Moreno (dmartmorsoft@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.games2;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import com.nokia.mid.ui.*;
import com.games2.Unidades;

/**
 *
 * <p>Title: MapaJuego </p>
 * <p>Description: Clase abstracta los métodos para el mapa </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Diego Martin Moreno @ 2003 </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public abstract class MapaJuego {

  /**
   * Nombre del Mapa del juego
   * @return Nombre del Mapa
   */
  public abstract String nameMap();

  /**
   * Pone en la matriz los objetos para pintar
   * @param matrizMapa Matriz del mapa con sus objetos
   */
  public abstract void setMapa(int[][] matrizMapa);

 }