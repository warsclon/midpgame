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
import com.games2.*;
/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company:  Diego Martín Moreno @ 2003  </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public class InfoUnidad {

  public static void setInfo(Graphics g,int[][][] mapa, int matrizX, int matrizY, Image[] imgUnidades) {
    g.setStrokeStyle(Graphics.SOLID);
    g.setColor(255, 255, 255);
    g.fillRect(15, 5, 70, 50);
    g.setColor(0, 0, 0);
    g.drawRect(15, 5, 70, 50);
    g.setFont(Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_SMALL));
    switch (mapa[matrizX][matrizY][0]) {
      case Unidades.CASA:
        g.drawString("CASA", 20, 10, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imgUnidades[Unidades.CASA], 74, 7, Graphics.LEFT | Graphics.TOP);
        break;
      case Unidades.TANQUE:
        g.drawString("tanque", 20, 10, Graphics.LEFT | Graphics.TOP);
        g.drawString("ataque:2", 20, 20, Graphics.LEFT | Graphics.TOP);
        g.drawString("experiencia:"+mapa[matrizX][matrizY][1], 20, 30, Graphics.LEFT | Graphics.TOP);
        g.drawString("vida:"+mapa[matrizX][matrizY][2], 20, 40, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imgUnidades[Unidades.TANQUE], 74, 7, Graphics.LEFT | Graphics.TOP);
        break;
      case Unidades.TANQUE_ENEMIGO:
        g.drawString("tanque", 20, 10, Graphics.LEFT | Graphics.TOP);
        g.drawString("ataque:2", 20, 20, Graphics.LEFT | Graphics.TOP);
        g.drawString("experiencia:"+mapa[matrizX][matrizY][1], 20, 30, Graphics.LEFT | Graphics.TOP);
        g.drawString("vida:"+mapa[matrizX][matrizY][2], 20, 40, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imgUnidades[Unidades.TANQUE_ENEMIGO], 74, 7, Graphics.LEFT | Graphics.TOP);
        break;
      case Unidades.AMETRALLADORA_ENEMIGO:
        g.drawString("ametralladora", 20, 10, Graphics.LEFT | Graphics.TOP);
        g.drawString("ataque:2", 20, 20, Graphics.LEFT | Graphics.TOP);
        g.drawString("experiencia:"+mapa[matrizX][matrizY][1], 20, 30, Graphics.LEFT | Graphics.TOP);
        g.drawString("vida:"+mapa[matrizX][matrizY][2], 20, 40, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imgUnidades[Unidades.AMETRALLADORA_ENEMIGO], 74, 7, Graphics.LEFT | Graphics.TOP);
        break;
      case Unidades.AMETRALLADORA:
        g.drawString("ametralladora", 20, 10, Graphics.LEFT | Graphics.TOP);
        g.drawString("ataque:2", 20, 20, Graphics.LEFT | Graphics.TOP);
        g.drawString("experiencia:"+mapa[matrizX][matrizY][1], 20, 30, Graphics.LEFT | Graphics.TOP);
        g.drawString("vida:"+mapa[matrizX][matrizY][2], 20, 40, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imgUnidades[Unidades.AMETRALLADORA], 74, 7, Graphics.LEFT | Graphics.TOP);
        break;
      case Unidades.SOLDADO:
        g.drawString("soldado", 20, 10, Graphics.LEFT | Graphics.TOP);
        g.drawString("ataque:1", 20, 20, Graphics.LEFT | Graphics.TOP);
        g.drawString("experiencia:"+mapa[matrizX][matrizY][1], 20, 30, Graphics.LEFT | Graphics.TOP);
        g.drawString("vida:"+mapa[matrizX][matrizY][2], 20, 40, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imgUnidades[Unidades.SOLDADO], 74, 7, Graphics.LEFT | Graphics.TOP);
        break;
      case Unidades.SOLDADO_ENEMIGO:
        g.drawString("soldado", 20, 10, Graphics.LEFT | Graphics.TOP);
        g.drawString("ataque:1", 20, 20, Graphics.LEFT | Graphics.TOP);
        g.drawString("experiencia:"+mapa[matrizX][matrizY][1], 20, 30, Graphics.LEFT | Graphics.TOP);
        g.drawString("vida:"+mapa[matrizX][matrizY][2], 20, 40, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imgUnidades[Unidades.SOLDADO], 74, 7, Graphics.LEFT | Graphics.TOP);
        break;
      case Unidades.ARBOL:
        g.drawString("arbol", 20, 10, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imgUnidades[Unidades.ARBOL], 74, 7, Graphics.LEFT | Graphics.TOP);
        break;
      default:
        g.drawString("suelo", 20, 10, Graphics.LEFT | Graphics.TOP);
        break;
    }

  }
}