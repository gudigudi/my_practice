<?php
// 引入页面需要的类文件
require_once('html_class.php');
require_once('table_class.php');
require_once('form_class.php');

// 将准备用到的类实例化
$HTMLPage = new html("GuestBook Page");
$MyTable = new table();
$MyForm = new form();

// 开始一个有边框且左对齐，宽度为30%的表格
$webpage = $MyTable->Begin(1, "left", "500");
$webpage .= $MyTable->RowOn();
$webpage .= $MyTable->ColumnOn();
$webpage .= $MyForm->Begin();  // "proof" of polymorphism
$webpage .= $MyForm->InputLabel("FirstName", "frame", true);
$webpage .= $MyTable->ColumnOff();
$webpage .= $MyTable->ColumnOn(1, "left");
$webpage .= $MyForm->Input("text", "frame", "", "", 30);
$webpage .= $MyTable->ColumnOff();
$webpage .= $MyTable->RowOff();

$webpage .= $MyForm->form_end();
$webpage .= $MyTable->End();

$webpage .= $HTMLPage->page_end();

echo $webpage;
?>
