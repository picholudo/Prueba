import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EdadTipoPruebaComponent } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba.component';
import { EdadTipoPruebaService } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba.service';
import { EdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';

describe('Component Tests', () => {
  describe('EdadTipoPrueba Management Component', () => {
    let comp: EdadTipoPruebaComponent;
    let fixture: ComponentFixture<EdadTipoPruebaComponent>;
    let service: EdadTipoPruebaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EdadTipoPruebaComponent],
        providers: []
      })
        .overrideTemplate(EdadTipoPruebaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EdadTipoPruebaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EdadTipoPruebaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EdadTipoPrueba(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.edadTipoPruebas && comp.edadTipoPruebas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
