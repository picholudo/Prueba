import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { PuntosCorteComponent } from 'app/entities/puntos-corte/puntos-corte.component';
import { PuntosCorteService } from 'app/entities/puntos-corte/puntos-corte.service';
import { PuntosCorte } from 'app/shared/model/puntos-corte.model';

describe('Component Tests', () => {
  describe('PuntosCorte Management Component', () => {
    let comp: PuntosCorteComponent;
    let fixture: ComponentFixture<PuntosCorteComponent>;
    let service: PuntosCorteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PuntosCorteComponent],
        providers: []
      })
        .overrideTemplate(PuntosCorteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuntosCorteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuntosCorteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PuntosCorte(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.puntosCortes && comp.puntosCortes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
