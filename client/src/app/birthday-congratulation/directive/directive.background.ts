import {Directive, ElementRef, HostBinding, HostListener, Input, OnInit, Renderer2} from '@angular/core';


@Directive({
  selector: '[appBackground]',

})

export class BackgroundDirective implements OnInit{


  @Input() colour:string;

  //= '#78ab46'

  //@HostBinding ('style.backgroundColor') background = this.colour;

  constructor(private element: ElementRef, private renderer:Renderer2, ) {
  }

  ngOnInit()  {
    this.renderer.setStyle(this.element.nativeElement , 'background-color', this.colour)
  }

  ngOnChange() {
    this.renderer.setStyle(this.element.nativeElement , 'background-color', this.colour)
  }

  // @HostListener('click') onMouseClick(){
  //   this.background = this.colour;
  // }

  // @HostListener('unload') loading(){
  //   this.background = this.colour
  // }
  @HostListener('change') changing(){
    this.renderer.setStyle(this.element.nativeElement , 'background-color', this.colour)
  }

}
