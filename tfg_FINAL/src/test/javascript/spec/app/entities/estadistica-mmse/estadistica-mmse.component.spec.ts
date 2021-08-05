import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticaMMSEComponent } from 'app/entities/estadistica-mmse/estadistica-mmse.component';
import { EstadisticaMMSEService } from 'app/entities/estadistica-mmse/estadistica-mmse.service';
import { EstadisticaMMSE } from 'app/shared/model/estadistica-mmse.model';

describe('Component Tests', () => {
  describe('EstadisticaMMSE Management Component', () => {
    let comp: EstadisticaMMSEComponent;
    let fixture: ComponentFixture<EstadisticaMMSEComponent>;
    let service: EstadisticaMMSEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaMMSEComponent],
        providers: []
      })
        .overrideTemplate(EstadisticaMMSEComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaMMSEComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaMMSEService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EstadisticaMMSE(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticaMMSES && comp.estadisticaMMSES[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
