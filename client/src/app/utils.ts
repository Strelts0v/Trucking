export class Utils {

  static dateToString(date: Date): string {
    function parse(s) {
      return (s < 10) ? '0' + s : s;
    }

    return date && `${parse(date.getDate())}.${parse(date.getMonth() + 1)}.${date.getFullYear()}`;
  }
}
