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
import javax.microedition.lcdui.Graphics;
import com.nokia.mid.ui.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company:  Diego Martín Moreno @ 2003  </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public class PintaObjetos {

  public Image image;

  public void enMapa(Graphics g, int mapa[][][], Image imageColeccion[], int matrizXmapa, int matrizYmapa) {
    //Pinta mapa
    for (int x = matrizXmapa; x < matrizXmapa + 9; x++) {
      for (int y = matrizYmapa; y < matrizYmapa +6; y++) {
        switch (mapa[x][y][0]) {
          case Unidades.TANQUE:
            g.drawImage(imageColeccion[Unidades.TANQUE], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
          case Unidades.SOLDADO:
            g.drawImage(imageColeccion[Unidades.SOLDADO], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
          case Unidades.AMETRALLADORA:
            g.drawImage(imageColeccion[Unidades.AMETRALLADORA], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
          case Unidades.TANQUE_ENEMIGO:
            g.drawImage(imageColeccion[Unidades.TANQUE_ENEMIGO], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
          case Unidades.SOLDADO_ENEMIGO:
            g.drawImage(imageColeccion[Unidades.SOLDADO_ENEMIGO], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
          case Unidades.AMETRALLADORA_ENEMIGO:
            g.drawImage(imageColeccion[Unidades.AMETRALLADORA_ENEMIGO], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
          case Unidades.CASA:

            g.drawImage(imageColeccion[Unidades.CASA], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
          case Unidades.ARBOL:
            g.drawImage(imageColeccion[Unidades.ARBOL], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
          case Unidades.CONQUISTA:
            g.drawImage(imageColeccion[Unidades.CONQUISTA], (10 * (x-matrizXmapa)) + 3,
                        (10 * (y-matrizYmapa)) + 3,
                        Graphics.LEFT | Graphics.TOP);
            break;
        }
      }
    }

  }
}