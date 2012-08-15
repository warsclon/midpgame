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
import com.games2.*;

/**
 *
 * <p>Title: CreaMapa </p>
 * <p>Description: Contiene los datos de la matriz que se pinta en el mapa </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Diego Martin Moreno @ 2003 </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public class CreaMapa {


  public static void setMapa(int[][][] matrizMapa, int juego) {

    switch (juego) {
      case 1: //barbarroja
        //
        matrizMapa[2][2][0] = Unidades.TANQUE; //tipo de unidad = 0
        matrizMapa[2][2][1] = 3; // experiencia = 1
        matrizMapa[2][2][2] = 10; // vida = 2
        //
        //
        matrizMapa[2][6][0] = Unidades.TANQUE;
        matrizMapa[2][6][1] = 3;
        matrizMapa[2][6][2] = 10;
        //
        //
        matrizMapa[3][8][0] = Unidades.TANQUE;
        matrizMapa[3][8][1] = 3;
        matrizMapa[3][8][2] = 10;
        //
        matrizMapa[1][3][0] = Unidades.SOLDADO; //tipo de unidad = 0
        matrizMapa[1][3][1] = 3; // experiencia = 1
        matrizMapa[1][3][2] = 4; // vida = 2
        //
        //
        matrizMapa[2][7][0] = Unidades.SOLDADO; //tipo de unidad = 0
        matrizMapa[2][7][1] = 3; // experiencia = 1
        matrizMapa[2][7][2] = 4; // vida = 2
        //
        //
        matrizMapa[1][1][0] = Unidades.AMETRALLADORA; //tipo de unidad = 0
        matrizMapa[1][1][1] = 3; // experiencia = 1
        matrizMapa[1][1][2] = 6; // vida = 2
        //
        //
        matrizMapa[1][6][0] = Unidades.AMETRALLADORA; //tipo de unidad = 0
        matrizMapa[1][6][1] = 3; // experiencia = 1
        matrizMapa[1][6][2] = 6; // vida = 2
        //

        //ENEMIGO
        //
        matrizMapa[8][1][0] = Unidades.TANQUE_ENEMIGO;
        matrizMapa[8][1][1] = 3;
        matrizMapa[8][1][2] = 10;
        //
        //
        matrizMapa[8][5][0] = Unidades.TANQUE_ENEMIGO;
        matrizMapa[8][5][1] = 3;
        matrizMapa[8][5][2] = 10;
        //
        //
        matrizMapa[8][7][0] = Unidades.TANQUE_ENEMIGO;
        matrizMapa[8][7][1] = 3;
        matrizMapa[8][7][2] = 10;
        //
        //soldado
        //
        matrizMapa[10][1][0] = Unidades.SOLDADO_ENEMIGO; //tipo de unidad = 0
        matrizMapa[10][1][1] = 3; // experiencia = 1
        matrizMapa[10][1][2] = 4; // vida = 2
        //
        //
        matrizMapa[10][6][0] = Unidades.SOLDADO_ENEMIGO; //tipo de unidad = 0
        matrizMapa[10][6][1] = 3; // experiencia = 1
        matrizMapa[10][6][2] = 4; // vida = 2
        //
        //
        matrizMapa[8][4][0] = Unidades.SOLDADO_ENEMIGO; //tipo de unidad = 0
        matrizMapa[8][4][1] = 3; // experiencia = 1
        matrizMapa[8][4][2] = 4; // vida = 2
        //
        //ametralladora
        //
        matrizMapa[10][2][0] = Unidades.AMETRALLADORA_ENEMIGO; //tipo de unidad = 0
        matrizMapa[10][2][1] = 3; // experiencia = 1
        matrizMapa[10][2][2] = 6; // vida = 2
        //ametralladora
        //
        matrizMapa[10][7][0] = Unidades.AMETRALLADORA_ENEMIGO; //tipo de unidad = 0
        matrizMapa[10][7][1] = 3; // experiencia = 1
        matrizMapa[10][7][2] = 6; // vida = 2
        //
        //arboles
        matrizMapa[0][2][0] = Unidades.ARBOL;
        matrizMapa[0][6][0] = Unidades.ARBOL;
        matrizMapa[2][8][0] = Unidades.ARBOL;
        matrizMapa[4][5][0] = Unidades.ARBOL;
        matrizMapa[5][1][0] = Unidades.ARBOL;
        matrizMapa[5][2][0] = Unidades.ARBOL;
        matrizMapa[5][5][0] = Unidades.ARBOL;
        matrizMapa[7][8][0] = Unidades.ARBOL;
        matrizMapa[8][8][0] = Unidades.ARBOL;
        matrizMapa[9][4][0] = Unidades.ARBOL;
        matrizMapa[9][5][0] = Unidades.ARBOL;
        matrizMapa[9][5][0] = Unidades.ARBOL;
        //casa
        matrizMapa[1][8][0] = Unidades.CASA;
        matrizMapa[4][2][0] = Unidades.CASA;
        matrizMapa[4][6][0] = Unidades.CASA;
        matrizMapa[9][3][0] = Unidades.CASA;
        matrizMapa[10][4][0] = Unidades.CASA;
        matrizMapa[10][5][0] = Unidades.CASA;
        //Conquista
        matrizMapa[12][4][0] = Unidades.CONQUISTA;
        break;
      case 2: //Estalingrado
        //
        matrizMapa[0][0][0] = Unidades.TANQUE; //tipo de unidad = 0
        matrizMapa[0][0][1] = 3; // experiencia = 1
        matrizMapa[0][0][2] = 10; // vida = 2
        //
        matrizMapa[1][2][0] = Unidades.TANQUE_ENEMIGO;
        matrizMapa[1][2][1] = 3;
        matrizMapa[1][2][2] = 10;
        //
        //
        matrizMapa[0][4][0] = Unidades.TANQUE;
        matrizMapa[0][4][1] = 3;
        matrizMapa[0][4][2] = 10;
        //
        //soldado
        //
        matrizMapa[7][1][0] = Unidades.SOLDADO; //tipo de unidad = 0
        matrizMapa[7][1][1] = 3; // experiencia = 1
        matrizMapa[7][1][2] = 10; // vida = 2
        //
        //
        matrizMapa[8][4][0] = Unidades.SOLDADO_ENEMIGO; //tipo de unidad = 0
        matrizMapa[8][4][1] = 3; // experiencia = 1
        matrizMapa[8][4][2] = 10; // vida = 2
        //
        //ametralladora
        //
        matrizMapa[5][2][0] = Unidades.AMETRALLADORA_ENEMIGO; //tipo de unidad = 0
        matrizMapa[5][2][1] = 3; // experiencia = 1
        matrizMapa[5][2][2] = 10; // vida = 2
        //
        //
        matrizMapa[5][3][0] = Unidades.AMETRALLADORA_ENEMIGO; //tipo de unidad = 0
        matrizMapa[5][3][1] = 3; // experiencia = 1
        matrizMapa[5][3][2] = 10; // vida = 2
        //
        //arboles
        matrizMapa[1][1][0] = Unidades.ARBOL;
        matrizMapa[8][0][0] = Unidades.ARBOL;
        matrizMapa[7][2][0] = Unidades.ARBOL;
        //casa
        matrizMapa[7][0][0] = Unidades.CASA;
        matrizMapa[3][2][0] = Unidades.CASA;
        matrizMapa[6][4][0] = Unidades.CASA;
        //Conquista
        matrizMapa[7][4][0] = Unidades.CONQUISTA;
        break;
    }
  }
 }