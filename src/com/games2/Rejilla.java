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
public class Rejilla {

  public static void setRejilla(Graphics g) {
    //rejilla del juego
    g.drawLine(0, 0, 0, 100);
    g.drawLine(1, 0, 1, 100);
    g.drawLine(2, 0, 2, 100);
    g.drawLine(93, 0, 93, 100);
    g.drawLine(94, 0, 94, 100);
    g.drawLine(95, 0, 95, 100);
    g.drawLine(0, 0, 100, 0);
    g.drawLine(0, 1, 100, 1);
    g.drawLine(0, 2, 100, 2);
    g.drawLine(0, 63, 100, 63);
    g.drawLine(0, 64, 100, 64);
    g.drawLine(0, 65, 100, 65);
    for (int i = 0; i < 100; i++) {
      if (i % 10 == 0) {
        g.drawLine(i + 2, 0, i + 2, 100);
      }
    }
    for (int i = 0; i < 110; i++) {
      if (i % 10 == 0) {
        g.drawLine(0, i + 2, 100, i + 2);
      }
    }

  }
}