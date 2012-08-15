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
import com.games2.Mapa;

/**
 *
 * <p>Title: WarGame </p>
 * <p>Description: Clase princial del juego WarGame para MIDP  para el 3410 de Nokia </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Diego Martín Moreno @ 2003 </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public class WarGame
    extends MIDlet {

  //Clases

  //Clase con el mapa
  private Mapa mapa;
  //Clase con el menu de selección
  private Menu MenuOp;
  //Clase con la presentación inicial
  private Presentacion presentacion;
  //Resultado ataque
  private ResultadoAtaque resultadoAtaque;

  //Variables de la clase

  //Pantalla
  public static Display display;

  /**
   * Inicializa la clase aqui
   */
  public WarGame() {

    //Instancia objeto con presentación
    // y mapa del juego
    presentacion = new Presentacion(this);
    mapa = new Mapa(this);

  }

  /**
   * Arranque de la aplicación
   * @throws MIDletStateChangeException Excepción al arrancar la aplicación
   */
  public void startApp() throws MIDletStateChangeException {
    display = Display.getDisplay(this);
    display.setCurrent(presentacion);
  }

  /**
   * Muestra en la pantalla el mapa del juego
   */
  public void pantallaJuego(int juego) {
    presentacion = null;
    mapa.setJuego(juego);
    display.setCurrent(mapa);
  }

  /**
   * Muestra en la pantalla el mapa del juego
   */
  public void volverJuego() {
    display.setCurrent(mapa);
  }

  public void pantallaAtaque(int eI, int eF, int aI, int aF, Image iE, Image iA) {
    resultadoAtaque = new ResultadoAtaque(this, eI, eF, aI, aF, iE, iA);
    display.setCurrent(resultadoAtaque);
  }

  /**
   * Muestra en la pantalla el menu del juego
   */
  public void pantallaMenu() {
    MenuOp = new Menu(display, this);
  }

  /**
   * Muestra en la pantalla con los datos del autor
   */
  public void pantallaAutor() {
    Autor autor = new Autor(this);
    display.setCurrent(autor);
  }

  /**
   * Cierra la aplicación
   * @param condicional pasar true para que se cierre
   */
  public void destroyApp(boolean condicional) {

  }

  /**
   * Pausa de la aplicación
   */
  public void pauseApp() {

  }

}