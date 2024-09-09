@file:JsModule("react-slot-counter")
@file:JsNonModule

package ru.posidata.views.utils.externals.slotmachine

import react.*

@JsName("default")  // Ensures you're importing the default export
external val Slot: FC<SlotProps>  // Declare the component

// External Props definition that maps to your TypeScript interface
external interface SlotProps : Props {
    var index: Int
    var isNew: Boolean?
    var charClassName: String?
    var numbersRef: RefObject<dynamic>
    var active: Boolean
    var isChanged: Boolean
    var effectiveDuration: Double
    var delay: Double
    var value: dynamic
    var startValue: dynamic?
    var disableStartValue: Boolean?
    var dummyList: Array<dynamic>
    var hasSequentialDummyList: Boolean?
    var hasInfiniteList: Boolean?
    var valueClassName: String?
    var numberSlotClassName: String?
    var numberClassName: String?
    var reverse: Boolean?
    var sequentialAnimationMode: Boolean
    var useMonospaceWidth: Boolean
    var maxNumberWidth: Int?
    var onFontHeightChange: ((Int) -> Unit)?
    var speed: Double
    var duration: Double
}
