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

/**
 *
 * <p>Title: Presentacion </p>
 * <p>Description: Clase runnable que muestra una pantalla con scroll </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company:  Diego Martín Moreno @ 2003  </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public class ResultadoAtaque
    extends FullCanvas
    implements Runnable {

  //Objeto midlet
  MIDlet midlet;

  //variable para controlar la animación
  boolean anima = true;

  //variable para controlar la animación
  boolean positivo = true;

  int contador;
  //Imagen de la presentación
  Image fondo;

  //Posición X de inicio de la animación
  int posX = -90;

  //Tiempo de espera de la animación
  int sleepTh = 50;
  boolean moverFondo = true;
  //Hilo de la clase runnable
  Thread hiloAni;

  int enemigoFinal;
  int enemigoInicial;
  int amigoFinal;
  int amigoInicial;

  int deslIz;
  int deslDer = 96;

  Image imageEnemigo;
  Image imageAmigo;

  /**
   * Constructor de la presentación y se le pasa
   * el objeto midlet
   * @param mlet Objeto MIDlet
   */
  public ResultadoAtaque(MIDlet m, int eI, int eF, int aI, int aF, Image iE,
                         Image iA) {
    midlet = m;
    enemigoFinal = eF;
    System.out.println("enemigoFinal:" + enemigoFinal);

    enemigoInicial = eI;
    System.out.println("enemigoInicial:" + enemigoInicial);
    amigoFinal = aF;
    System.out.println("amigoFinal:" + amigoFinal);
    amigoInicial = aI;
    System.out.println("amigoInicial:" + amigoInicial);
    imageEnemigo = iE;
    imageAmigo = iA;
    System.out.println("resultadoAtaq");

    try {

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
    g.setClip(0, 0, deslIz, 65);
    g.setStrokeStyle(Graphics.SOLID);
    g.setColor(255, 255, 255);
    g.fillRect(0, 0, deslIz, 65);
    g.setClip(deslDer, 0, 96 - deslDer, 65);
    g.fillRect(deslDer, 0, 96 - deslDer, 65);
    g.setColor(0, 0, 0);
    g.drawLine(deslIz, 0, deslIz, 65);
    g.drawLine(96 - deslDer, 0, 96 - deslDer, 65);

    if (deslIz == 48) { //Pintamos los soldados
      g.setClip(0, 0, getWidth(), getHeight());

      if (contador < 5) { //inicial
                  g.drawImage(imageAmigo, 5, 45, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imageEnemigo, 81, 45, Graphics.LEFT | Graphics.TOP);
        g.drawString("" + amigoInicial, 30, 10, Graphics.LEFT | Graphics.TOP);
        g.drawString("" + enemigoInicial, 66, 10, Graphics.LEFT | Graphics.TOP);
      }
      else {
        g.drawImage(imageAmigo, 5, 45, Graphics.LEFT | Graphics.TOP);
        g.drawImage(imageEnemigo, 81, 45, Graphics.LEFT | Graphics.TOP);
        g.drawString("" + amigoFinal, 30, 10, Graphics.LEFT | Graphics.TOP);
        g.drawString("" + enemigoFinal, 66, 10, Graphics.LEFT | Graphics.TOP);
      }
    }

  }

  /**
   * Método run que mueve la presentación
   */
  public void run() {

    while (anima) {

      if (deslIz == 48) {
        try {
          repaint();
          hiloAni.sleep(500);
          contador++;
          if (contador > 10) {
            stop();
          }
        }
        catch (Exception ex) {
          System.out.println("error:" + ex);
        }
      }
      else {
        try {
          hiloAni.sleep(sleepTh);
        }
        catch (Exception ex) {
        }

        deslIz++;
        deslDer--;
        repaint();

      }
    }

  }

  public void stop() {
    anima = false;
    contador = 0;
    deslIz = 0;
    deslDer = 96;
    ( (WarGame) midlet).volverJuego();
  }

}