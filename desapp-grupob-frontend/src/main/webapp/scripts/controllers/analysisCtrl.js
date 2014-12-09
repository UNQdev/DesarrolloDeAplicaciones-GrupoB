function analysisCtrl($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs,
                       resolvedCategoriesIncomingTotals, resolvedCategoriesOutcomingTotals,
                       resolvedSubCategoriesIncomingTotals, resolvedSubCategoriesOutcomingTotals,
                       resolvedMonthIncomingAccrual, resolvedMonthOutcomingAccrual,
                       resolvedVendorsTotals
                      ) {

    
    
    /*
     *  MONTH CHARTS
     */
    console.log('MONTH Incoming ACCRUAL ' + resolvedMonthIncomingAccrual.data);

    var monthIncomingAccrual = resolvedMonthIncomingAccrual.data;

    var monthIncomingAccrual_keys = new Array();
    var monthIncomingAccrual_values = new Array();
    for (var key in monthIncomingAccrual) {
        monthIncomingAccrual_keys.push(key);
        monthIncomingAccrual_values.push(monthIncomingAccrual[key]);
    }

    console.log(monthIncomingAccrual);
    console.log(monthIncomingAccrual_keys);
    console.log(monthIncomingAccrual_values);

    $scope.monthIncomingAccrualData = {
        labels: monthIncomingAccrual_keys,
        datasets: [
            {
                label: 'My First dataset',
                fillColor: 'rgba(220,220,220,0.2)',
                strokeColor: 'rgba(220,220,220,1)',
                pointColor: 'rgba(220,220,220,1)',
                pointStrokeColor: '#fff',
                pointHighlightFill: '#fff',
                pointHighlightStroke: 'rgba(220,220,220,1)',
                data: monthIncomingAccrual_values
            }
        ]
    }; 

    $scope.monthIncomingAccrualOptions =  {

        // Sets the chart to be responsive
        responsive: true,

        ///Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - Whether the line is curved between points
        bezierCurve : true,

        //Number - Tension of the bezier curve between points
        bezierCurveTension : 0.4,

        //Boolean - Whether to show a dot for each point
        pointDot : true,

        //Number - Radius of each point dot in pixels
        pointDotRadius : 4,

        //Number - Pixel width of point dot stroke
        pointDotStrokeWidth : 1,

        //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
        pointHitDetectionRadius : 20,

        //Boolean - Whether to show a stroke for datasets
        datasetStroke : true,

        //Number - Pixel width of dataset stroke
        datasetStrokeWidth : 2,

        //Boolean - Whether to fill the dataset with a colour
        datasetFill : true,

        // Function - on animation progress
        onAnimationProgress: function(){},

        // Function - on animation complete
        onAnimationComplete: function(){},

        //String - A legend template
        legendTemplate : '<ul class="tc-chart-js-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].strokeColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };
    
    console.log('MONTH Outcoming ACCRUAL ' + resolvedMonthOutcomingAccrual.data);

    var monthOutcomingAccrual = resolvedMonthOutcomingAccrual.data;

    var monthOutcomingAccrual_keys = new Array();
    var monthOutcomingAccrual_values = new Array();
    for (var key in monthOutcomingAccrual) {
        monthOutcomingAccrual_keys.push(key);
        monthOutcomingAccrual_values.push(monthOutcomingAccrual[key]);
    }

    console.log(monthOutcomingAccrual);
    console.log(monthOutcomingAccrual_keys);
    console.log(monthOutcomingAccrual_values);

    $scope.monthOutcomingAccrualData = {
        labels: monthOutcomingAccrual_keys,
        datasets: [
            {
                label: 'My First dataset',
                fillColor: 'rgba(220,220,220,0.2)',
                strokeColor: 'rgba(220,220,220,1)',
                pointColor: 'rgba(220,220,220,1)',
                pointStrokeColor: '#fff',
                pointHighlightFill: '#fff',
                pointHighlightStroke: 'rgba(220,220,220,1)',
                data: monthOutcomingAccrual_values
            }
        ]
    }; 

    $scope.monthOutcomingAccrualOptions =  {

        // Sets the chart to be responsive
        responsive: true,

        ///Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - Whether the line is curved between points
        bezierCurve : true,

        //Number - Tension of the bezier curve between points
        bezierCurveTension : 0.4,

        //Boolean - Whether to show a dot for each point
        pointDot : true,

        //Number - Radius of each point dot in pixels
        pointDotRadius : 4,

        //Number - Pixel width of point dot stroke
        pointDotStrokeWidth : 1,

        //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
        pointHitDetectionRadius : 20,

        //Boolean - Whether to show a stroke for datasets
        datasetStroke : true,

        //Number - Pixel width of dataset stroke
        datasetStrokeWidth : 2,

        //Boolean - Whether to fill the dataset with a colour
        datasetFill : true,

        // Function - on animation progress
        onAnimationProgress: function(){},

        // Function - on animation complete
        onAnimationComplete: function(){},

        //String - A legend template
        legendTemplate : '<ul class="tc-chart-js-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].strokeColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };
    
    
    
    
    /*
     *  CATEGORIES CHARTS
     */
    console.log('CATEGORIES Incoming TOTALS ' + resolvedCategoriesIncomingTotals.data);

    var categoriesIncoming = resolvedCategoriesIncomingTotals.data;

    var categoriesIncoming_keys = new Array();
    var categoriesIncoming_values = new Array();
    for (var key in categoriesIncoming) {
        categoriesIncoming_keys.push(key);
        categoriesIncoming_values.push(categoriesIncoming[key]);
    }
    
    console.log(categoriesIncoming);
    console.log(categoriesIncoming_keys);
    console.log(categoriesIncoming_values);
    
    $scope.categoriesIncomingTotalsData = {
        labels: categoriesIncoming_keys,
        datasets: [
            {
                label: 'CATEGORIES Incoming TOTALS',
                fillColor: 'rgba(220,220,220,0.5)',
                strokeColor: 'rgba(220,220,220,0.8)',
                highlightFill: 'rgba(220,220,220,0.75)',
                highlightStroke: 'rgba(220,220,220,1)',
                data: categoriesIncoming_values
            }
        ]
    };

    $scope.categoriesIncomingTotalsOptions =  {

        // Sets the chart to be responsive
        responsive: true,

        //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
        scaleBeginAtZero : true,

        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - If there is a stroke on each bar
        barShowStroke : true,

        //Number - Pixel width of the bar stroke
        barStrokeWidth : 2,

        //Number - Spacing between each of the X value sets
        barValueSpacing : 5,

        //Number - Spacing between data sets within X values
        barDatasetSpacing : 1,

        //String - A legend template
        legendTemplate : '<ul class="tc-chart-js-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };

    console.log('CATEGORIES Outcoming TOTALS ' + resolvedCategoriesOutcomingTotals.data);  
    
    var categoriesOutcoming = resolvedCategoriesOutcomingTotals.data;

    var categoriesOutcoming_keys = new Array();
    var categoriesOutcoming_values = new Array();
    for (var key in categoriesOutcoming) {
        categoriesOutcoming_keys.push(key);
        categoriesOutcoming_values.push(categoriesOutcoming[key]);
    }

    console.log(categoriesOutcoming);
    console.log(categoriesOutcoming_keys);
    console.log(categoriesOutcoming_values);

    $scope.categoriesOutcomingTotalsData = {
        labels: categoriesOutcoming_keys,
        datasets: [
            {
                label: 'CATEGORIES Outcoming TOTALS',
                fillColor: 'rgba(220,220,220,0.5)',
                strokeColor: 'rgba(220,220,220,0.8)',
                highlightFill: 'rgba(220,220,220,0.75)',
                highlightStroke: 'rgba(220,220,220,1)',
                data: categoriesOutcoming_values
            }
        ]
    };

    $scope.categoriesOutcomingTotalsOptions =  {

        // Sets the chart to be responsive
        responsive: true,

        //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
        scaleBeginAtZero : true,

        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - If there is a stroke on each bar
        barShowStroke : true,

        //Number - Pixel width of the bar stroke
        barStrokeWidth : 2,

        //Number - Spacing between each of the X value sets
        barValueSpacing : 5,

        //Number - Spacing between data sets within X values
        barDatasetSpacing : 1,

        //String - A legend template
        legendTemplate : '<ul class="tc-chart-js-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };



    /*
     *  SUBCATEGORIES CHARTS
     */
    console.log('SUBCATEGORIES Incoming TOTALS ' + resolvedSubCategoriesIncomingTotals.data);

    var subcategoriesIncoming = resolvedSubCategoriesIncomingTotals.data;

    var subcategoriesIncoming_keys = new Array();
    var subcategoriesIncoming_values = new Array();
    for (var key in subcategoriesIncoming) {
        subcategoriesIncoming_keys.push(key);
        subcategoriesIncoming_values.push(subcategoriesIncoming[key]);
    }

    console.log(subcategoriesIncoming);
    console.log(subcategoriesIncoming_keys);
    console.log(subcategoriesIncoming_values);
    
    $scope.subcategoriesIncomingTotalsData = {
        labels: subcategoriesIncoming_keys,
        datasets: [
            {
                label: 'SUBCATEGORIES Incoming TOTALS',
                fillColor: 'rgba(220,220,220,0.5)',
                strokeColor: 'rgba(220,220,220,0.8)',
                highlightFill: 'rgba(220,220,220,0.75)',
                highlightStroke: 'rgba(220,220,220,1)',
                data: subcategoriesIncoming_values
            }
        ]
    };


    $scope.subcategoriesIncomingTotalsOptions =  {

        // Sets the chart to be responsive
        responsive: true,

        //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
        scaleBeginAtZero : true,

        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - If there is a stroke on each bar
        barShowStroke : true,

        //Number - Pixel width of the bar stroke
        barStrokeWidth : 2,

        //Number - Spacing between each of the X value sets
        barValueSpacing : 5,

        //Number - Spacing between data sets within X values
        barDatasetSpacing : 1,

        //String - A legend template
        legendTemplate : '<ul class="tc-chart-js-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };
    
    

    console.log('SUBCATEGORIES Outcoming TOTALS ' + resolvedSubCategoriesIncomingTotals.data);

    var subcategoriesOutcoming = resolvedSubCategoriesOutcomingTotals.data;

    var subcategoriesOutcoming_keys = new Array();
    var subcategoriesOutcoming_values = new Array();
    for (var key in subcategoriesIncoming) {
        subcategoriesOutcoming_keys.push(key);
        subcategoriesOutcoming_values.push(subcategoriesOutcoming[key]);
    }

    console.log(subcategoriesOutcoming);
    console.log(subcategoriesOutcoming_keys);
    console.log(subcategoriesOutcoming_values);

    $scope.subcategoriesOutcomingTotalsData = {
        labels: subcategoriesOutcoming_keys,
        datasets: [
            {
                label: 'SUBCATEGORIES Outcoming TOTALS',
                fillColor: 'rgba(220,220,220,0.5)',
                strokeColor: 'rgba(220,220,220,0.8)',
                highlightFill: 'rgba(220,220,220,0.75)',
                highlightStroke: 'rgba(220,220,220,1)',
                data: subcategoriesOutcoming_values
            }
        ]
    };

    $scope.subcategoriesOutcomingTotalsOptions =  {

        // Sets the chart to be responsive
        responsive: true,

        //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
        scaleBeginAtZero : true,

        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - If there is a stroke on each bar
        barShowStroke : true,

        //Number - Pixel width of the bar stroke
        barStrokeWidth : 2,

        //Number - Spacing between each of the X value sets
        barValueSpacing : 5,

        //Number - Spacing between data sets within X values
        barDatasetSpacing : 1,

        //String - A legend template
        legendTemplate : '<ul class="tc-chart-js-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };
    
    
    
    
    
    
    
    
    
    
    
    
    
    






    //TEST PORPOUSE
    
    $scope.data = [
        {
            value: 300,
            color:'#F7464A',
            highlight: '#FF5A5E',
            label: 'Red'
        },
        {
            value: 50,
            color: '#46BFBD',
            highlight: '#5AD3D1',
            label: 'Green'
        },
        {
            value: 100,
            color: '#FDB45C',
            highlight: '#FFC870',
            label: 'Yellow'
        },
        {
            value: 40,
            color: '#949FB1',
            highlight: '#A8B3C5',
            label: 'Grey'
        },
        {
            value: 120,
            color: '#4D5360',
            highlight: '#616774',
            label: 'Dark Grey'
        }
    ];

    // Chart.js Options
    $scope.options =  {

        // Sets the chart to be responsive
        responsive: true,

        //Boolean - Show a backdrop to the scale label
        scaleShowLabelBackdrop : true,

        //String - The colour of the label backdrop
        scaleBackdropColor : 'rgba(255,255,255,0.75)',

        // Boolean - Whether the scale should begin at zero
        scaleBeginAtZero : true,

        //Number - The backdrop padding above & below the label in pixels
        scaleBackdropPaddingY : 2,

        //Number - The backdrop padding to the side of the label in pixels
        scaleBackdropPaddingX : 2,

        //Boolean - Show line for each value in the scale
        scaleShowLine : true,

        //Boolean - Stroke a line around each segment in the chart
        segmentShowStroke : true,

        //String - The colour of the stroke on each segement.
        segmentStrokeColor : '#fff',

        //Number - The width of the stroke value in pixels
        segmentStrokeWidth : 2,

        //Number - Amount of animation steps
        animationSteps : 100,

        //String - Animation easing effect.
        animationEasing : 'easeOutBounce',

        //Boolean - Whether to animate the rotation of the chart
        animateRotate : true,

        //Boolean - Whether to animate scaling the chart from the centre
        animateScale : false,

        //String - A legend template
        legendTemplate : '<ul class="tc-chart-js-legend"><% for (var i=0; i<segments.length; i++){%><li><span style="background-color:<%=segments[i].fillColor%>"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>'
    };






}