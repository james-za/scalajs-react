package japgolly.scalajs.react

import scala.scalajs.js

object Addons {

  object ReactCssTransitionGroup {
    /** Items in the CSSTransitionGroup need this attribute for animation to work properly. */
    @inline final def key = vdom.Attrs.key
  }

  case class ReactCssTransitionGroup(name     : String,
                                     enter    : js.UndefOr[Boolean] = js.undefined,
                                     leave    : js.UndefOr[Boolean] = js.undefined,
                                     component: js.UndefOr[String]  = js.undefined,
                                     ref      : js.UndefOr[String]  = js.undefined) {
    def toJs: js.Object = {
      val p = js.Dynamic.literal("transitionName" -> name)
      enter    .foreach(p.updateDynamic("transitionEnter")(_))
      leave    .foreach(p.updateDynamic("transitionLeave")(_))
      component.foreach(p.updateDynamic("component"      )(_))
      ref      .foreach(p.updateDynamic("ref"            )(_))
      p
    }

    def apply(children: ReactNode*): ReactComponentU_ = {
      val f = React.addons.CSSTransitionGroup
      f(toJs, children.toJsArray).asInstanceOf[ReactComponentU_]
    }
  }

  object ReactCloneWithProps {

    def mapToJS(props: Map[String, js.Any]): js.Object = {
      val obj = js.Dynamic.literal()
      props.foreach { case (key, value) =>
        obj.updateDynamic(key)(value)
      }
      obj
    }

    def apply(child: ReactNode, newProps: Map[String, js.Any]) = {
      val f = React.addons.cloneWithProps
      f(child, mapToJS(newProps)).asInstanceOf[ReactComponentU_]
    }
  }

}
