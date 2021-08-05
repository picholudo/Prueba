import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticaFABComponent } from 'app/entities/estadistica-fab/estadistica-fab.component';
import { EstadisticaFABService } from 'app/entities/estadistica-fab/estadistica-fab.service';
import { EstadisticaFAB } from 'app/shared/model/estadistica-fab.model';

describe('Component Tests', () => {
  describe('EstadisticaFAB Management Component', () => {
    let comp: EstadisticaFABComponent;
    let fixture: ComponentFixture<EstadisticaFABComponent>;
    let service: EstadisticaFABService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaFABComponent],
        providers: []
      })
        .overrideTemplate(EstadisticaFABComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaFABComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaFABService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EstadisticaFAB(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticaFABS && comp.estadisticaFABS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
