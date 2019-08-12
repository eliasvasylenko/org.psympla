package org.topiello.scanner.text;

import org.topiello.scanner.Scanner;
import org.topiello.text.Text;
import org.topiello.text.TextUnit;

public interface TextScanner<C extends TextUnit> extends Scanner<C, Text<C>> {}
