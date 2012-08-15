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
import com.nokia.mid.ui.FullCanvas;

/**
 *
 * <p>Title: Presentacion </p>
 * <p>Description: Clase runnable que muestra una pantalla con scroll </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company:  Diego Martín Moreno @ 2003  </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public class Presentacion
    extends FullCanvas
    implements Runnable {

  //Objeto midlet
  MIDlet midlet;

  //variable para controlar la animación
  boolean anima = true;
  boolean salir;
  
  //Imagen de la presentación
  Image fondo;
  int disparo = 1;
  //Posición X de inicio de la animación
  int posX = -90;

  //Tiempo de espera de la animación
  int sleepTh = 20;

  //Hilo de la clase runnable
  Thread hiloAni;

  /**
   * Constructor de la presentación y se le pasa
   * el objeto midlet
   * @param mlet Objeto MIDlet
   */
  public Presentacion(MIDlet mlet) {
    midlet = mlet;
    try {
      fondo = Image.createImage("/deslizante.png");
      hiloAni = new Thread(this);
    }
    catch (Exception ex) {
    }
    hiloAni.start();
  }

  /**
   * Pinta la pantalla de la presentación
   * @param g Objeto graphics
   */
  public void paint(Graphics g) {
    //g.setColor(255, 255, 255);
    g.setColor(0,0,0);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.drawImage(fondo, posX, 0, Graphics.LEFT | Graphics.TOP);
    if (posX > -1 && !anima) {
      g.setColor(255, 255, 255);
      if (disparo > 0)
        g.fillArc(80, 20, 7, 7, 0, 360);
      if (disparo > 1)
        g.fillArc(70, 30, 7, 7, 0, 360);
      if (disparo > 2)
        g.fillArc(85, 40, 7, 7, 0, 360);
    }
  }

  /**
   * Método run que mueve la presentación
   */
  public void run() {
    while (anima) {
      posX = posX + 1;
      repaint();
      try {
        hiloAni.sleep(sleepTh);
      }
      catch (Exception ex) {
      }
      if (posX > -1) {

        try {
          hiloAni.sleep(500);
          anima = false;
          repaint();
          hiloAni.sleep(1000);
          disparo++;
          repaint();
          hiloAni.sleep(500);
          disparo++;
          repaint();
          hiloAni.sleep(500);
          salir = true;
        }
        catch (Exception ex) {
          System.out.println("error:" + ex);
        }
      }
    }

  }

  /**
   * Eventos de teclado
   */
  protected void keyPressed(int keyCode) {
    if (salir)
      ( (WarGame) midlet).pantallaMenu();
  }

}