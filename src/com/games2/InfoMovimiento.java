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
public class InfoMovimiento {

  public static void setInfo(Graphics g, int i, int turnoMapa) {
    g.setStrokeStyle(Graphics.SOLID);
    g.setColor(255, 255, 255);
    g.fillRect(20, 20, 55, 20);
    g.setColor(0, 0, 0);
    g.drawRect(20, 20, 55, 20);
    if (i == 1) {
      g.drawString("mueve...", 27, 22, Graphics.LEFT | Graphics.TOP);
    }
    else if (i == 2) {
      g.drawString("ataque...", 27, 22, Graphics.LEFT | Graphics.TOP);
    }
    else if (i == 3) {
      g.drawString("turno:" + turnoMapa, 27, 22, Graphics.LEFT | Graphics.TOP);
    }
    else {
      g.drawString("fin turno " + turnoMapa, 27, 22,
                   Graphics.LEFT | Graphics.TOP);
    }
  }
}