# Markdown to HTML Converter (md2html)

This project is a Markdown to HTML converter written in Java, utilizing object-oriented principles. The converter supports basic Markdown elements such as headings, paragraphs, text emphasis, strikethrough, inline code, and more.

## Features

- **Text paragraphs**: Separated by empty lines.
- **Inline formatting**:
  - Emphasis: `*text*` or `_text_`
  - Strong emphasis: `**text**` or `__text__`
  - Strikethrough: `--text--`
  - Inline code: `` `code` ``
- **Headings**: Supports headings from level 1 to 6 (`#`, `##`, `###`, etc.).
- **Multiline headings**: Headings can span multiple lines.
- **Escaping special characters**: Proper escaping of `<`, `>`, and `&` in HTML.
- **Extra feature**: Code block support using triple backticks `` ``` ``.

## Usage

The converter is called `md2html.Md2Html` and takes two arguments:

1. Name of the input file with Markdown markup.
2. Name of the output file for the resulting HTML.

Both files must use UTF-8 encoding.
