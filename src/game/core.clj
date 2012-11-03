(ns game.core
  (:import org.lwjgl.LWJGLException
           [org.lwjgl.opengl Display DisplayMode GL11]
           [org.lwjgl.util.glu GLU])
  (:gen-class :main true))

(def ^:dynamic *width* 800)
(def ^:dynamic *height* 600)
(def ^:dynamic *fps* 60)

(defn init-gl [width height]
  (Display/setDisplayMode (new DisplayMode width height))
  (Display/setTitle "clojure-lwjgl")
  (Display/setFullscreen false)
  (Display/setVSyncEnabled true)
  (Display/create)
  
  (GL11/glEnable GL11/GL_TEXTURE_2D)
  (GL11/glShadeModel GL11/GL_SMOOTH)
  (GL11/glClearColor 0.0 0.0 0.0 0.0)
  (GL11/glClearDepth 1.0)
  (GL11/glEnable GL11/GL_DEPTH_TEST)
  (GL11/glDepthFunc GL11/GL_LEQUAL)
  
  (GL11/glMatrixMode GL11/GL_PROJECTION)
  (GL11/glLoadIdentity)
  
  (GLU/gluPerspective 45.0 (/ width height) 0.1 100.0)
  
  (GL11/glMatrixMode GL11/GL_MODELVIEW)
  (GL11/glHint GL11/GL_PERSPECTIVE_CORRECTION_HINT GL11/GL_NICEST))


(defn render-cube []
  (GL11/glTranslatef 0.0 0.0 -7.0)             
  (GL11/glRotatef    45.0 1.0 0.5 0.5)               
  (GL11/glColor3f    0.5 0.5 1.0)         
  
  (GL11/glBegin GL11/GL_QUADS)    
    (GL11/glColor3f    1.0  1.0  0.0)           
    (GL11/glVertex3f   1.0  1.0 -1.0)        
    (GL11/glVertex3f  -1.0  1.0 -1.0)        
    (GL11/glVertex3f  -1.0  1.0  1.0)
    (GL11/glVertex3f   1.0  1.0  1.0)  
    (GL11/glColor3f    1.0  0.5  0.0)            
    (GL11/glVertex3f   1.0 -1.0  1.0)
    (GL11/glVertex3f  -1.0 -1.0  1.0)
    (GL11/glVertex3f  -1.0 -1.0 -1.0)
    (GL11/glVertex3f   1.0 -1.0 -1.0)
    (GL11/glColor3f    1.0  0.0  0.0)
    (GL11/glVertex3f   1.0  1.0  1.0)
    (GL11/glVertex3f  -1.0  1.0  1.0)
    (GL11/glVertex3f  -1.0 -1.0  1.0)
    (GL11/glVertex3f   1.0 -1.0  1.0)
    (GL11/glColor3f    1.0  1.0  0.0)
    (GL11/glVertex3f   1.0 -1.0 -1.0)
    (GL11/glVertex3f  -1.0 -1.0 -1.0)
    (GL11/glVertex3f  -1.0  1.0 -1.0)
    (GL11/glVertex3f   1.0  1.0 -1.0)
    (GL11/glColor3f    0.0  0.0  1.0)
    (GL11/glVertex3f  -1.0  1.0  1.0)
    (GL11/glVertex3f  -1.0  1.0 -1.0)
    (GL11/glVertex3f  -1.0 -1.0 -1.0)
    (GL11/glVertex3f  -1.0 -1.0  1.0)
    (GL11/glColor3f    1.0  0.0  1.0)
    (GL11/glVertex3f   1.0  1.0 -1.0)
    (GL11/glVertex3f   1.0  1.0  1.0)
    (GL11/glVertex3f   1.0 -1.0  1.0)
    (GL11/glVertex3f   1.0 -1.0 -1.0)
  (GL11/glEnd))

(defn render [width height]
      ; Clear the screen and depth buffer
	    (GL11/glClear (or GL11/GL_COLOR_BUFFER_BIT GL11/GL_DEPTH_BUFFER_BIT))	
      (GL11/glLoadIdentity)
      (render-cube))

(defn run []
  (init-gl *width* *height*)
  
  (loop [close? (Display/isCloseRequested)]
    (if-not close? 
      (do
        (render *width* *height*)
        (Display/update)
        (Display/sync *fps*)
        (recur (Display/isCloseRequested)))))
  
  (Display/destroy))


(defn -main [& args]
  (future (run)))
