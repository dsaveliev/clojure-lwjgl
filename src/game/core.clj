(ns game.core
  (:import org.lwjgl.LWJGLException
           [org.lwjgl.opengl Display DisplayMode GL11]
           [org.lwjgl.util.glu GLU])
  (:gen-class :main true))

(def ^:dynamic *width* 800)
(def ^:dynamic *height* 600)

(defn start [width height]
  (Display/setDisplayMode (new DisplayMode width height))
  (Display/setTitle "clojure-lwjgl")
  (Display/setFullscreen false)
  (Display/setVSyncEnabled true)
  (Display/create)
  
  (loop [close? (Display/isCloseRequested)]
    (if-not close? 
      (do
        (Display/update)
        (recur (Display/isCloseRequested)))))
  
  (Display/destroy))

(defn -main [& args]
  (future (start *width* *height*)))
