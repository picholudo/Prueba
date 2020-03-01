import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticasDetailComponent } from 'app/entities/estadisticas/estadisticas-detail.component';
import { Estadisticas } from 'app/shared/model/estadisticas.model';

describe('Component Tests', () => {
  describe('Estadisticas Management Detail Component', () => {
    let comp: EstadisticasDetailComponent;
    let fixture: ComponentFixture<EstadisticasDetailComponent>;
    const route = ({ data: of({ estadisticas: new Estadisticas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticas on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
