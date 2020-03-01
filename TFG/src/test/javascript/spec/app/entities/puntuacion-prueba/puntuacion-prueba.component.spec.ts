import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { PuntuacionPruebaComponent } from 'app/entities/puntuacion-prueba/puntuacion-prueba.component';
import { PuntuacionPruebaService } from 'app/entities/puntuacion-prueba/puntuacion-prueba.service';
import { PuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';

describe('Component Tests', () => {
  describe('PuntuacionPrueba Management Component', () => {
    let comp: PuntuacionPruebaComponent;
    let fixture: ComponentFixture<PuntuacionPruebaComponent>;
    let service: PuntuacionPruebaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PuntuacionPruebaComponent],
        providers: []
      })
        .overrideTemplate(PuntuacionPruebaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuntuacionPruebaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuntuacionPruebaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PuntuacionPrueba(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.puntuacionPruebas && comp.puntuacionPruebas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
