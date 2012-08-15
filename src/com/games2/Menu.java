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
import com.nokia.mid.sound.Sound;
import com.games2.Juegos;

/**
 * <p>Title: Menu</p>
 * <p>Description: Clase con el menu de la aplicación </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Diego Martín Moreno @ 2003 </p>
 * @author Diego Martín Moreno
 * @version 0.01
 */
public class Menu
    extends Form
    implements CommandListener {
  //opciones del menu
  private String opciones[] = {"Barbarroja", "Estalindrado", "Autor"};

  //Lista del menu
  List mOpciones = new List("War Games", Choice.IMPLICIT, opciones, null);

  //Midlet para llamar a los mapas
  MIDlet midlet;

  //pantalla donde se muestra el menu
  Display display;

  //Comando salir de la aplicación
  static final Command salir = new Command("salir", Command.EXIT, 1);

  /**
   * Constructor del menu
   * @param d Display para mostrar el menu
   * @param m Midlet para llamar a otras clases
   */
  public Menu(Display d, MIDlet m) {
    super("War Games");
    display = d;
    midlet = m;
    mOpciones.addCommand(salir);
    mOpciones.setCommandListener(this);
    d.setCurrent(mOpciones);
  }

  public void commandAction(Command c, Displayable d) {
    if (c == List.SELECT_COMMAND) {
      switch (mOpciones.getSelectedIndex()) {
        case 0:
          ((WarGame)midlet).pantallaJuego(Juegos.BARBARROJA);
          break;
        case 1:
          ((WarGame)midlet).pantallaJuego(Juegos.ESTALINGRADO);
          break;
        case 2:
          ((WarGame)midlet).pantallaAutor();
          break;
      }
    }
  }
 }
