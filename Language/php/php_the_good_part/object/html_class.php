<?php
class html {
    // 首页定义类的属性(变量)
    private $tag;

    // 接下来定义方法(类中的函数)
    function __construct($title = "") {
        // 类的构造函数，每次实例化时都会被调用
        $this->tag = "<html>";
        $this->tag .= "<head>";
        $this->tag .= "<title> $title </title>";
        $this->tag .= "</head><body>";
        echo $this->tag;
        return ;
    }
    function page_end() {
        // 结束html页面，关闭body和html标签
        $this->tag = "</body></html>";
        return $this->tag;
    }
    function RawText($textString, $textColor="blank", $bgcolor="",
        $fontSize="", $fontWeight="normal") {
        // 此方法用来将未格式化的文字内容发送到浏览器
        $this->tag = "<span style='color: $textColor; background-color: $bgcolor;
                font-size: $fontSize; font-weight: $fontWeight'>";
        $this->tag .= "$textString";
        $this->tag .= "</span>";
        return $this->tag;
    }
    function Image($source, $title="", $height="", $width="", $align="center",
        $border=0, $valign="middle", $class="", $id="", $name="", $onType1="",
        $onAction1="", $onType2="", $onAction2="", $onType3="", $onAction3="") {
        // 此方法用来在HTML页面中添加图片标签，它有三个
        // on开头的事件(onclick、onblur、onmouseup)
            $this->tag = '<img src="' . $source . '" ';
            if ($name) $this->tag .= 'name="' . $name . '" ';
            if ($height == "") $height = "16";
            if ($width == "") $weight = "16";
            $this->tag .= 'height="' . $height . '" width="' . $width . '" ';
            $this->tag .= 'border="' . $border . '" ';
            if ($class) $this->tag .= 'class="' . $class . '" ';
            if ($id) $this->tag .= 'id="' . $id . '" ';
            if ($title) $this->tag .= 'title="' . $title . '" alt="' . $title . '" ';
            if ($align) $this->tag .= 'align="' . $align . '" ';
            if ($valign) $this->tag .= 'valign="' . $valign . '" ';
            if ($onType1) $this->tag .= $onType1 . '="' . $onAction1 .'" ';
            if ($onType2) $this->tag .= $onType2 . '="' . $onAction2 .'" ';
            if ($onType3) $this->tag .= $onType3 . '="' . $onAction3 .'" ';
            $this->tag .= "/>";
            return $this->tag;
    }
    function Spacer($spaces = 1) {
        $this->tag = "";
        for ($i = 1; $i <= $spaces; $i++) {
            $this->tag .= "&nbsp;";
        }
        return $this->tag;
    }
    function NewLine($number = 1) {
        $this->tag = '';
        for ($i = 1; $i <= $number; $i++) {
            $this->tag .= "<br />";
        }
        return $this->tag;
    }
} // html类结束
?>
