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
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Canvas;
import java.util.*;
import java.io.*;
import com.nokia.mid.ui.FullCanvas;
import com.nokia.mid.sound.*;
import com.games2.*;

/**
 * <p>Title: Mapa </p>
 * <p>Description: Clase donde se gestionan los mapas </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Diego Martín Moreno @ 2003 </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public class Mapa
    extends FullCanvas
    implements Runnable {

  //Midlet
  MIDlet midlet;

  //Posicion inicio del visor en el canvas
  int posX = 42;
  int posY = 32;

  //Posicion inicio del visor en la matriz
  int matrizX = 6;
  int matrizY = 5;

  //Posicion inicio de pintado en la matriz arriba -derecha
  int matrizXmapa = 2;
  int matrizYmapa = 2;

  //Control para cuando el visor esta en el borde
  boolean moverDerecha = false;
  boolean moverIzquierda = false;
  boolean moverAbajo = false;
  boolean moverArriba = false;

  //mostrar turno
  boolean mostrarTurno = true;

  //daño ataque
  int daño;

  //Objeto temporal que se va a mover a
  //una nueva posición
  int unidadTemp;
  int vidaTemp;
  int experienciaTemp;
  int unidadTempAmigo;
  int vidaTempAmigo;
  int experienciaTempAmigo;

  //Posición de origen del objeto
  private int posXorigen;
  private int posYorigen;

  //Posicion de origen del disparo
  //se podra usar la variable anterior???
  int posXorigenDisparo;
  int posYorigenDisparo;

  //ataque
  int amigoInicial;
  int amigoFinal;
  int enemigoInicial;
  int enemigoFinal;
  int enemigoUnidad;
  int amigoUnidad;

  //Para ataque unidades
  Random random = new Random();

  //Info de la unidad
  boolean mostrarInfoUnidad;

  //Info para mover unidad
  boolean mostrarMover = true;

//No muestra  mas el mensaje de movimiento
  boolean mostrarMasMover = true;

  //Control para atacar
  boolean ataquePrimero = true;

  //No muestra mas ataque cuando buscas objetivo
  boolean mostrarAtaque = true;

  //Objeto para pintar el mapa
  PintaObjetos pintaObjetos;

  //Matriz del mapa mapa de 13 de ancho y 10 de largo
  int mapa[][][] = new int[13][10][4];

  // Turnos mapa
  int turnoMapa = 10;
  // Movimientos del turno
  int movTurno = 1;
  int movTurnoEnemigo = 8;
  int movTurnoTemp = 0;

  //Posición de los dato en la matriz
  final int UNIDAD = 0;
  final int EXPERIENCIA = 1;
  final int VIDA = 2;
  final int TURNO = 3;

  //nombre del mapa
  String nameMapa = new String();

  //Id del juego
  int juego;
  int mostrarVisor = Unidades.VISOR;

  //Imagenes del juego
  private Image[] imageColeccion = new Image[11];
  Thread hiloMapa;
  boolean hiloRun = true;
  static boolean hacerAtaque = true;

  public void run() {
    while (hiloRun) {

      if (mostrarVisor == Unidades.VISOR) {
        mostrarVisor = Unidades.VISOR2;
      }
      else {
        mostrarVisor = Unidades.VISOR;

      }
      repaint();
      try {
        Thread.sleep(500);

      }
      catch (Exception ex) {

      }

    }

  }

  public void setJuego(int j) {

    juego = j;
    nameMapa = com.games2.Juegos.getJuego(j);
    CreaMapa.setMapa(mapa, juego);

    //Carga las imagenes
    try {
      imageColeccion[Unidades.VISOR] = Image.createImage("/visor.png");
      imageColeccion[Unidades.VISOR2] = Image.createImage("/visor2.png");
      imageColeccion[Unidades.ARBOL] = Image.createImage("/arbol.png");
      imageColeccion[Unidades.CASA] = Image.createImage("/casa.png");
      imageColeccion[Unidades.TANQUE] = Image.createImage("/tanque.png");
      imageColeccion[Unidades.SOLDADO] = Image.createImage("/soldado.png");
      imageColeccion[Unidades.AMETRALLADORA] = Image.createImage(
          "/ametralladora.png");
      imageColeccion[Unidades.AMETRALLADORA_ENEMIGO] = Image.createImage(
          "/ametralladora_enemigo.png");
      imageColeccion[Unidades.SOLDADO_ENEMIGO] = Image.createImage(
          "/soldado_enemigo.png");
      imageColeccion[Unidades.TANQUE_ENEMIGO] = Image.createImage(
          "/tanque_enemigo.png");
      imageColeccion[Unidades.CONQUISTA] = Image.createImage("/conquistar.png");
    }
    catch (Exception ex) {
      System.out.println("error carga imagenes:" + ex);
    }
    //Inicializa el que pinta el mapa
    pintaObjetos = new PintaObjetos();

  }

  /**
   * Inicializa la clase aqui
   */
  public Mapa(MIDlet mlet) {
    midlet = mlet;
    //Hilo para animar el visor
    hiloMapa = new Thread(this);
    hiloMapa.start();
  }

  /**
   * Pinta el mapa
   */
  public void paint(Graphics g) {

    //Area de limpiado
    g.setClip(0, 0, getWidth(), getHeight());
    //color blanco
    g.setColor(255, 255, 255);
    //pinta fondo blanco
    g.fillRect(0, 0, getWidth(), getHeight());
    //pinta ahora en negro
    g.setColor(0, 0, 0);
    //marco del mapa
    g.drawRect(0, 0, 95, 64);

    //rejilla del mapa
    //Rejilla.setRejilla(g);

    //Pinta objetos
    pintaObjetos.enMapa(g, mapa, imageColeccion, matrizXmapa, matrizYmapa);

    //pinta visor
    g.drawImage(imageColeccion[mostrarVisor], posX, posY,
                Graphics.LEFT | Graphics.TOP);

    //Muestra info unidad
    if (mostrarInfoUnidad) {
      InfoUnidad.setInfo(g, mapa, matrizX, matrizY, imageColeccion);

    }

    //mensaje del movimiento
    if (!mostrarMover && mostrarMasMover) {
      InfoMovimiento.setInfo(g, 1, 0);
    }

    //mensaje de ataque
    if (!ataquePrimero && mostrarAtaque) {
      InfoMovimiento.setInfo(g, 2, 0);
    }

    //Mensaje de turno
    if (mostrarTurno) {
      InfoMovimiento.setInfo(g, 3, turnoMapa);
    }

    //Mensaje fin de turno
    if ( (movTurno == movTurnoTemp) && hacerAtaque) {
      hacerAtaque = false;
      turnoMapa--;

      InfoMovimiento.setInfo(g, 4, turnoMapa);
      movimientoEnemigo();
      resetTurno();
      movTurnoTemp = 0;
      hacerAtaque = true;
    }
    //Llama a la garbage collector
    System.gc();
  }

  /**
   * Eventos de teclado
   */
  protected void keyPressed(int keyCode) {

    if (mostrarInfoUnidad) {
      mostrarInfoUnidad = false;
    }
    if (mostrarTurno) {

      mostrarTurno = false;
    }
    if (!ataquePrimero && mostrarAtaque) {
      mostrarAtaque = false;
    }
    if (mostrarMasMover) {
      mostrarMasMover = false;
    }

    switch (keyCode) {
      /*
             case KEY_NUM3: //arriba - derecha
        if (posY > 8 && posX < 87) {
          posY = posY - 10;
          posX = posX + 10;
          matrizY = matrizY - 1;
          matrizX = matrizX + 1;
          repaint();
        }
        break;
             case KEY_NUM1: //arriba - izquierda
        if (posY > 8 && posX > 8) {
          posY = posY - 10;
          posX = posX - 10;
          matrizX = matrizX - 1;
          matrizY = matrizY - 1;
          repaint();
        }
        break;
             case KEY_NUM7: //abajo - izquierda
        if (posY < 56 && posX > 8) {
          posY = posY + 10;
          posX = posX - 10;
          matrizX = matrizX - 1;
          matrizY = matrizY + 1;
          repaint();
        }
        break;
             case KEY_NUM9: //abajo - derecha
        if (posY < 56 && posX < 87) {
          posY = posY + 10;
          posX = posX + 10;
          matrizX = matrizX + 1;
          matrizY = matrizY + 1;
          repaint();
        }
        break;
       */
      case KEY_NUM2: //arriba
        if (posY > 2) {
          posY = posY - 10;
          matrizY = matrizY - 1;
        }
        if (posY == 2) {
          if (!moverArriba) {
            moverArriba = true;
          }
          else {
            if (matrizY > 0) {
              matrizY = matrizY - 1;
            }
            if (matrizYmapa > 0) {
              matrizYmapa = matrizYmapa - 1;
            }
          }
        }
        repaint();
        break;
      case KEY_NUM8: //abajo
        if (posY < 52) {
          posY = posY + 10;
          matrizY = matrizY + 1;
        }
        if (posY == 52) {
          if (!moverAbajo) {
            moverAbajo = true;
          }
          else {
            if (matrizY < 9) {
              matrizY = matrizY + 1;
            }
            if (matrizYmapa < 4) {
              matrizYmapa = matrizYmapa + 1;
            }
          }
        }
        repaint();
        break;
      case KEY_NUM6: //DERECHA
        moverIzquierda = false;
        if (posX < 82) {
          posX = posX + 10;
          matrizX = matrizX + 1;
        }
        if (posX == 82) {
          if (!moverDerecha) {
            moverDerecha = true;
          }
          else {
            if (matrizX < 12) {
              matrizX = matrizX + 1;
            }
            if (matrizXmapa < 4) {
              matrizXmapa = matrizXmapa + 1;
            }
          }
        }
        repaint();
        break;
      case KEY_NUM4: //IZQUIERD
        moverDerecha = false;
        if (posX > 2) {
          posX = posX - 10;
          matrizX = matrizX - 1;
        }
        if (posX == 2) {
          if (!moverIzquierda) {
            moverIzquierda = true;
          }
          else {
            if (matrizX > 0) {
              matrizX = matrizX - 1;
            }
            if (matrizXmapa > 0) {
              matrizXmapa = matrizXmapa - 1;
            }
          }
        }
        repaint();
        break;
      case KEY_NUM5: //info de la casilla
        mostrarInfoUnidad = true;
        repaint();
        break;
      case KEY_POUND: //Boton de ataque
        if (ataquePrimero) {
          if ( (mapa[matrizX][matrizY][UNIDAD] == Unidades.AMETRALLADORA) ||
              (mapa[matrizX][matrizY][UNIDAD] == Unidades.SOLDADO) ||
              (mapa[matrizX][matrizY][UNIDAD] == Unidades.TANQUE) &&
              mapa[matrizX][matrizY][TURNO] == 0) {
           System.out.println("ataquePrimero:"+ataquePrimero+"_mostrarAtaque:"+mostrarAtaque);

            ataquePrimero = false;
            posXorigenDisparo = matrizX;
            posYorigenDisparo = matrizY;
            repaint();
          }
        }
        else {

          if ( (mapa[matrizX][matrizY][UNIDAD] ==
                Unidades.AMETRALLADORA_ENEMIGO) ||
              (mapa[matrizX][matrizY][UNIDAD] == Unidades.SOLDADO_ENEMIGO) ||
              (mapa[matrizX][matrizY][UNIDAD] == Unidades.TANQUE_ENEMIGO)) {
            if ( (mapa[posXorigenDisparo][posYorigenDisparo][UNIDAD] ==
                  Unidades.TANQUE)) {
              if ( (Math.abs(posXorigenDisparo - matrizX) < 3) &&
                  (Math.abs(posYorigenDisparo - matrizY) < 3)) { //movimientos permitido
                //calcula y muestra ataque
                ataque(posXorigenDisparo, posYorigenDisparo, matrizX, matrizY);
                //control turno
                mapa[posXorigenDisparo][posYorigenDisparo][TURNO] = 1;
                //turno
                movTurnoTemp++;
                //resultado ataque
              }
            }
            else {
              if ( (Math.abs(posXorigenDisparo - matrizX) < 2) &&
                  (Math.abs(posYorigenDisparo - matrizY) < 2)) { //movimientos permitido
                //control turno
                mapa[posXorigenDisparo][posYorigenDisparo][TURNO] = 1;
                ataque(posXorigenDisparo, posYorigenDisparo, matrizX, matrizY);
                //turno
                movTurnoTemp++;
              }

            }
          }
          ataquePrimero = true;
          mostrarAtaque = true;
        }
        break;
      case KEY_STAR: //mover tanque, ametralladora y soldado
        if (mostrarMover) {
          if ( (mapa[matrizX][matrizY][UNIDAD] == Unidades.AMETRALLADORA) ||
              (mapa[matrizX][matrizY][UNIDAD] == Unidades.SOLDADO) ||
              (mapa[matrizX][matrizY][UNIDAD] == Unidades.TANQUE) &&
              (mapa[matrizX][matrizY][TURNO] == 0)) {
            mostrarMover = false;
            mostrarMasMover = true;
            posXorigen = Integer.parseInt(Integer.toString(matrizX));
            posYorigen = Integer.parseInt(Integer.toString(matrizY));
            posXorigen = matrizX;
            posYorigen = matrizY;
            unidadTemp = mapa[posXorigen][posYorigen][UNIDAD];
            vidaTemp = mapa[posXorigen][posYorigen][VIDA];
            experienciaTemp = mapa[posXorigen][posYorigen][EXPERIENCIA];
          }
        }
        else {
          mostrarMover = true;
          if (mapa[matrizX][matrizY][UNIDAD] == 0) { //cuadricula vacia
            if ( (mapa[posXorigen][posYorigen][UNIDAD] == Unidades.TANQUE) ||
                (mapa[posXorigen][posYorigen][UNIDAD] ==
                 Unidades.TANQUE_ENEMIGO)) { //tanque = movimiento 2
              if ( (Math.abs(posXorigen - matrizX) < 3) &&
                  (Math.abs(posYorigen - matrizY) < 3)) { //movimientos permitido
                //Unidad
                mapa[matrizX][matrizY][UNIDAD] = unidadTemp;
                mapa[posXorigen][posYorigen][UNIDAD] = 0;
                //Vida
                mapa[matrizX][matrizY][VIDA] = vidaTemp;
                mapa[posXorigen][posYorigen][VIDA] = 0;
                //Experiencia
                mapa[matrizX][matrizY][EXPERIENCIA] = experienciaTemp;
                mapa[posXorigen][posYorigen][EXPERIENCIA] = 0;
                //turno
                mapa[matrizX][matrizY][TURNO] = 1;
                movTurnoTemp++;
              }
            }
            else { //soldados ametralladora = movimiento 1
              if ( (Math.abs(posXorigen - matrizX) < 2) &&
                  (Math.abs(posYorigen - matrizY) < 2)) { //movimientos permitido
                //Unidad
                mapa[matrizX][matrizY][UNIDAD] = unidadTemp;
                mapa[posXorigen][posYorigen][UNIDAD] = 0;
                //Vida
                mapa[matrizX][matrizY][VIDA] = vidaTemp;
                mapa[posXorigen][posYorigen][VIDA] = 0;
                //Experiencia
                mapa[matrizX][matrizY][EXPERIENCIA] = experienciaTemp;
                mapa[posXorigen][posYorigen][EXPERIENCIA] = 0;
                //turno
                mapa[matrizX][matrizY][TURNO] = 1;
                movTurnoTemp++;
              }
            }
          }

        }
        repaint();
        break;
      case -7: //KEY_SOFTKEY2: para salir
        ( (WarGame) midlet).pantallaMenu();
        break;
    }
  }

  public synchronized void ataque(int posXorigen, int posYorigen,
                                  int posXdestino, int posYdestino) {
    enemigoInicial = mapa[posXdestino][posYdestino][VIDA];
    amigoInicial = mapa[posXorigen][posYorigen][VIDA];
    enemigoUnidad = mapa[posXdestino][posYdestino][UNIDAD];
    amigoUnidad = mapa[posXorigen][posYorigen][UNIDAD];

    // System.out.println("enemigoInicial:"+enemigoInicial);
    //System.out.println("amigoInicial:"+amigoInicial);

    //El ataque enemigo
    if ( (mapa[posXorigen][posYorigen][UNIDAD] ==
          Unidades.TANQUE_ENEMIGO) ||
        (mapa[posXorigen][posYorigen][UNIDAD] == Unidades.TANQUE)) {
      daño = (Math.abs(random.nextInt() % 6));
    }
    else if ( (mapa[posXorigen][posYorigen][UNIDAD] ==
               Unidades.AMETRALLADORA) ||
             (mapa[posXorigen][posYorigen][UNIDAD] ==
              Unidades.AMETRALLADORA_ENEMIGO)) {
      daño = (Math.abs(random.nextInt() % 4));
    }
    else { //soldado
      daño = (Math.abs(random.nextInt() % 2));
    }
    //tirada salvacion
    if ( (mapa[posXdestino][posYdestino][UNIDAD] == Unidades.TANQUE_ENEMIGO) ||
        (mapa[posXdestino][posYdestino][UNIDAD] == Unidades.TANQUE)) {
      if (Math.abs(random.nextInt() % 6) > 3) {
        mapa[posXdestino][posYdestino][VIDA] = mapa[posXdestino][posYdestino][
            VIDA] - daño;
        enemigoFinal = mapa[posXdestino][posYdestino][VIDA];
      }
    }
    else if ( (mapa[posXdestino][posYdestino][UNIDAD] == Unidades.AMETRALLADORA) ||
             (mapa[posXdestino][posYdestino][UNIDAD] ==
              Unidades.AMETRALLADORA_ENEMIGO)) {
      if (Math.abs(random.nextInt() % 6) > 4) {
        mapa[posXdestino][posYdestino][VIDA] = mapa[posXdestino][posYdestino][
            VIDA] - daño;
        enemigoFinal = mapa[posXdestino][posYdestino][VIDA];
      }
    }
    else { //soldado
      if (Math.abs(random.nextInt() % 6) == 6) {
        mapa[posXdestino][posYdestino][VIDA] = mapa[posXdestino][posYdestino][
            VIDA] - daño;
        enemigoFinal = mapa[posXdestino][posYdestino][VIDA];
      }
    }
    //El ataque amigo
    if ( (mapa[posXdestino][posYdestino][UNIDAD] == Unidades.TANQUE_ENEMIGO) ||
        (mapa[posXdestino][posYdestino][UNIDAD] == Unidades.TANQUE)) {
      daño = (Math.abs(random.nextInt() % 6));
    }
    else if ( (mapa[posXdestino][posYdestino][UNIDAD] == Unidades.AMETRALLADORA) ||
             (mapa[posXdestino][posYdestino][UNIDAD] ==
              Unidades.AMETRALLADORA_ENEMIGO)) {
      daño = (Math.abs(random.nextInt() % 4));
    }
    else { //soldado
      daño = (Math.abs(random.nextInt() % 2));
    }
    //tirada salvacion
    if ( (mapa[posXorigen][posYorigen][UNIDAD] ==
          Unidades.TANQUE_ENEMIGO) ||
        (mapa[posXorigen][posYorigen][UNIDAD] == Unidades.TANQUE)) {
      if (Math.abs(random.nextInt() % 6) < 4) {
        mapa[posXorigen][posYorigen][VIDA] = mapa[posXorigen][posYorigen][VIDA] -
            daño;
        amigoFinal = mapa[posXorigen][posYorigen][VIDA];
      }
    }
    else if ( (mapa[posXorigen][posYorigen][UNIDAD] ==
               Unidades.AMETRALLADORA) ||
             (mapa[posXorigen][posYorigen][UNIDAD] ==
              Unidades.AMETRALLADORA_ENEMIGO)) {
      if (Math.abs(random.nextInt() % 6) < 5) {
        mapa[posXorigen][posYorigen][VIDA] = mapa[posXorigen][posYorigen][VIDA] -
            daño;
        amigoFinal = mapa[posXorigen][posYorigen][VIDA];
      }
    }
    else { //soldado
      if (Math.abs(random.nextInt() % 6) < 6) {
        mapa[posXorigen][posYorigen][VIDA] = mapa[posXorigen][posYorigen][VIDA] -
            daño;
        amigoFinal = mapa[posXorigen][posYorigen][VIDA];
      }
    }
    //System.out.println("amigoFinal:"+amigoFinal);
    //System.out.println("enemigoFinal:"+enemigoFinal);
    //resultado ataque
    ( (WarGame) midlet).pantallaAtaque(enemigoInicial, enemigoFinal,
                                       amigoInicial, amigoFinal,
                                       imageColeccion[enemigoUnidad],
                                       imageColeccion[amigoUnidad]);

  }

  public void resetTurno() {

    for (int x = 0; x < 13; x++) {
      for (int y = 0; y < 10; y++) {
        switch (mapa[x][y][UNIDAD]) {
          case Unidades.TANQUE:
            mapa[x][y][TURNO] = 0;
            break;
          case Unidades.SOLDADO:
            mapa[x][y][TURNO] = 0;
            break;
          case Unidades.AMETRALLADORA:
            mapa[x][y][TURNO] = 0;
            break;
        }
      }
    }
  }

  public void resetTurnoEnemigo() {

    for (int x = 0; x < 13; x++) {
      for (int y = 0; y < 10; y++) {
        switch (mapa[x][y][UNIDAD]) {
          case Unidades.TANQUE_ENEMIGO:
            mapa[x][y][TURNO] = 0;
            break;
          case Unidades.SOLDADO_ENEMIGO:
            mapa[x][y][TURNO] = 0;
            break;
          case Unidades.AMETRALLADORA_ENEMIGO:
            mapa[x][y][TURNO] = 0;
            break;
        }
      }
    }
  }

  public synchronized void movimientoEnemigo() {

    //aleatorio
    uno:for (int n = 0; n < movTurnoEnemigo; n++) {
      for (int x = 0; x < 13; x++) {
        for (int y = 0; y < 10; y++) {
          //System.out.println("xxxxxxxxxxxxxxxxxxx:"+x);
          switch (mapa[x][y][UNIDAD]) {
            case Unidades.TANQUE_ENEMIGO:

              if ( (mapa[x][y][TURNO] == 0) && //mueve si no ha pasado el turno y no hay enemigo para atacar
                  atacarEnemigo(x, y, Unidades.TANQUE_ENEMIGO)) {
                boolean movimiento = true;
                while (movimiento) {
                  //posicion
                  int posXn = Math.abs(random.nextInt() % 3);
                  int posYn = Math.abs(random.nextInt() % 3);
                  //negativo x
                  if ( (Math.abs(random.nextInt() % 2) == 0) && (posXn > 0)) {
                    posXn = posXn * -1;
                    //negativo y
                  }
                  if ( (Math.abs(random.nextInt() % 2) == 0) && (posYn > 0)) {
                    posYn = posYn * -1;
                    //
                  }
                  if ( ( ( (x + posXn) < 13) && ( (x + posXn) > -1))
                      && ( ( (y + posYn) < 10) && ( (y + posYn) > -1))) {
                    if (mapa[x + posXn][y + posYn][UNIDAD] == 0) {
                      //Unidad
                      mapa[x + posXn][y +
                          posYn][UNIDAD] = Unidades.TANQUE_ENEMIGO;
                      mapa[x][y][UNIDAD] = 0;
                      //Vida
                      mapa[x + posXn][y + posYn][VIDA] = mapa[x][y][VIDA];
                      //Experiencia
                      mapa[x + posXn][y +
                          posYn][EXPERIENCIA] = mapa[x][y][EXPERIENCIA];
                      //turno
                      mapa[x + posXn][y + posYn][TURNO] = 1;
                      continue uno;
                    }

                  }
                }
                mapa[x][y][TURNO] = 1;

              }
              break;
            case Unidades.SOLDADO_ENEMIGO:
              if ( (mapa[x][y][TURNO] == 0) &&
                  atacarEnemigo(x, y, Unidades.SOLDADO_ENEMIGO)) {
                boolean movimiento = true;
                while (movimiento) {
                  //posicion
                  int posXn = Math.abs(random.nextInt() % 2);
                  int posYn = Math.abs(random.nextInt() % 2);
                  //negativo x
                  if ( (Math.abs(random.nextInt() % 2) == 0) && (posXn > 0)) {
                    posXn = posXn * -1;
                    //negativo y
                  }
                  if ( (Math.abs(random.nextInt() % 2) == 0) && (posYn > 0)) {
                    posYn = posYn * -1;
                    //
                  }
                  if ( ( ( (x + posXn) < 13) && ( (x + posXn) > -1))
                      && ( ( (y + posYn) < 10) && ( (y + posYn) > -1))) {
                    if (mapa[x + posXn][y + posYn][UNIDAD] == 0) {
                      //Unidad
                      mapa[x + posXn][y +
                          posYn][UNIDAD] = Unidades.SOLDADO_ENEMIGO;
                      mapa[x][y][UNIDAD] = 0;
                      //Vida
                      mapa[x + posXn][y + posYn][VIDA] = mapa[x][y][VIDA];
                      //Experiencia
                      mapa[x + posXn][y +
                          posYn][EXPERIENCIA] = mapa[x][y][EXPERIENCIA];
                      //turno
                      mapa[x + posXn][y + posYn][TURNO] = 1;
                      continue uno;
                    }
                  }
                }
              }
              break;
            case Unidades.AMETRALLADORA_ENEMIGO:
              if ( (mapa[x][y][TURNO] == 0) &&
                  atacarEnemigo(x, y, Unidades.AMETRALLADORA_ENEMIGO)) {
                boolean movimiento = true;
                while (movimiento) {
                  //posicion
                  int posXn = Math.abs(random.nextInt() % 2);
                  int posYn = Math.abs(random.nextInt() % 2);
                  //negativo x
                  if ( (Math.abs(random.nextInt() % 2) == 0) && (posXn > 0)) {
                    posXn = posXn * -1;
                    //negativo y
                  }
                  if ( (Math.abs(random.nextInt() % 2) == 0) && (posYn > 0)) {
                    posYn = posYn * -1;
                    //
                  }
                  if ( ( ( (x + posXn) < 13) && ( (x + posXn) > -1))
                      && ( ( (y + posYn) < 10) && ( (y + posYn) > -1))) {
                    if (mapa[x + posXn][y + posYn][UNIDAD] == 0) {
                      //Unidad
                      mapa[x + posXn][y +
                          posYn][UNIDAD] = Unidades.AMETRALLADORA_ENEMIGO;
                      mapa[x][y][UNIDAD] = 0;
                      //Vida
                      mapa[x + posXn][y + posYn][VIDA] = mapa[x][y][VIDA];
                      //Experiencia
                      mapa[x + posXn][y +
                          posYn][EXPERIENCIA] = mapa[x][y][EXPERIENCIA];
                      //turno
                      mapa[x + posXn][y + posYn][TURNO] = 1;
                      continue uno;
                    }
                  }
                }
              }
              break;
          }
        }
      }

    }
    resetTurnoEnemigo();
  }

  public synchronized boolean atacarEnemigo(int x, int y, int unidad) {
    boolean mover = true;
    boolean ataqueOk = true;
    if (unidad == Unidades.TANQUE_ENEMIGO) {
      for (int xE = x - 2; xE < x + 2; xE++) { //x
        for (int yE = y - 2; yE < y + 2; yE++) { //y
          if ( ( (xE > -1) && (xE < 13)) && ( (yE > -1) && (yE < 10))) {
            if ( (mapa[xE][yE][UNIDAD] == Unidades.TANQUE) ||
                (mapa[xE][yE][UNIDAD] == Unidades.AMETRALLADORA) ||
                (mapa[xE][yE][UNIDAD] == Unidades.SOLDADO)) {
              ataque(x, y, xE, yE);
              mapa[xE][yE][TURNO] = 1;
              if (ataqueOk) {
                ataqueOk = false;
              }
             mover = false;
            }
          }
        }
      }
    }
    else if (unidad == Unidades.AMETRALLADORA_ENEMIGO ||
             unidad == Unidades.SOLDADO_ENEMIGO) {
      for (int xE = x - 1; xE < x + 1; xE++) { //x
        for (int yE = y - 1; yE < y + 1; yE++) { //y
          if ( ( (xE > -1) && (xE < 13)) && ( (yE > -1) && (yE < 10))) {
            if ( (mapa[xE][yE][UNIDAD] == Unidades.TANQUE) ||
                (mapa[xE][yE][UNIDAD] == Unidades.AMETRALLADORA) ||
                (mapa[xE][yE][UNIDAD] == Unidades.SOLDADO)) {
              ataque(x, y, xE, yE);
              mapa[xE][yE][TURNO] = 1;
              if (ataqueOk) {
                ataqueOk = false;
              }
              mover = false;
            }
          }
        }
      }
    }
    return mover;
  }

  public void stop() {
    hiloRun = false;
  }

  public void arrancar() {
    hiloRun = true;
    hiloMapa.start();
  }

}