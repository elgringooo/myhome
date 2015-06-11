var DetailspeopleModule = function (contextPath, profile, isPnTabSelected) {

        // Private variable
        var CNP_TAB_INDEX = 0;
        var PN_TAB_INDEX = 1;
        var globalContextPath = contextPath;
        var globalProfile = profile;
        var isPnTabSelected = isPnTabSelected;
        var globalManageCircleNetworkUrl = contextPath + '/ManageCircleNetwork';
        var globalPersonalNetworkLoginUrl = globalContextPath + '/PersonalNetworkLogin';
        var globalPeopleDetailsUrl = globalContextPath + '/people/detailspeople.jsp?q=gin:';
        var globalAjaxCircleNetworkUsersUrl = 'ajaxCircleNetworkUsers.jsp';
        var globalAjaxPersonalNetworkOverviewUrl = 'ajaxPersonalNetworkOverview.jsp';
        var globalFindPeoplePopupUrl = 'findPeoplePopup.jsp';
        var $profileTabs = null;
        var $circleDialog = null;

        //selected circle id
        var selectedCnId = -1;

        return {
            // Begin Public Section
            init: function init() {

                //Initialize all scroll pane
                $('.scroll-pane').jScrollPane({
                    verticalDragMinHeight: 40,
                    verticalDragMaxHeight: 40
                });

                //Initialize  profile tabs and personal network block
                $profileTabs = $('#profileTabs');

                // Build the personal network tab
                buildPersonalNetworkTab();

                //Build the personal network block
                buildPersonalNetworkBlock();

                // Build the jquery tabs
                $profileTabs.tabs({

                    //On load this tab
                    load: function (event, ui) {

                        if (ui.index == CNP_TAB_INDEX) {

                            //Setup CNP scroll pane after loading
                            $('.scroll-pane-cnp').jScrollPane({
                                hijackInternalLinks: true,
                                verticalDragMinHeight: 40,
                                verticalDragMaxHeight: 40
                            });

                        } else if (ui.index == PN_TAB_INDEX) {
                            //Setup PN scroll pane after loading
                            $('#personal-network').jScrollPane({
                                verticalDragMinHeight: 40,
                                verticalDragMaxHeight: 40
                            });


                            // Force circle selection if postData selectedCnId  not empty
                            if (selectedCnId != null && selectedCnId > 0) {
                                var selector = '#pn-circles .pn-circle[cnId="' + selectedCnId + '"]';
                                $(selector).click();
                            }
                        }
                    },
                    // On select this tab			    
                    select: function (event, ui) {


                    },
                    ajaxOptions: {
                        type: 'POST',
                        data: globalProfile,
                        error: function (xhr, status, index, anchor) {
                            $(anchor.hash).html("Couldn't load this tab.");
                        }
                    }
                });

                if (isPnTabSelected) {
                    $profileTabs.tabs('select', PN_TAB_INDEX);
                }


            }
        };
        /**
         * Build the personal network block
         */
        function buildPersonalNetworkBlock() {
            var $personalNetworkOverviewCircle = $('#pn-circles-overwiew .pn-circle');
            $personalNetworkOverviewCircle.live('click', function () {
                selectedCnId = $(this).attr('cnId');
                var selectedTabIndex = $profileTabs.tabs('option', 'selected');
                if (selectedTabIndex != PN_TAB_INDEX) {
                    $profileTabs.tabs('select', PN_TAB_INDEX);
                }
                $profileTabs.tabs('load', PN_TAB_INDEX);
            });

            loadPersonalNetworkBlock($profileTabs)
        }

        /**
         * Load the personal network block
         */
        function loadPersonalNetworkBlock() {

            var $personalNetworkOverview = $('#personal-network-overview');


            $personalNetworkOverview.empty().addClass('loading');
            var formObj = {
                user: globalProfile.user
            };
            $personalNetworkOverview.load(globalAjaxPersonalNetworkOverviewUrl, formObj, function (data) {
                $personalNetworkOverview.removeClass('loading');

                //Update scrollpane after personal network block loading	
                $('#personal-network-overview .scroll-pane').jScrollPane({
                    verticalDragMinHeight: 40,
                    verticalDragMaxHeight: 40
                });
            });

        }

        /**
         * Load the personal network details
         */
        function loadPersonalNetworkDetails(id) {

            var $pnUsers = $('#pn-users');
            $pnUsers.empty().addClass('loading');

            $pnUsers.load(globalAjaxCircleNetworkUsersUrl, {
                id: id
            }, function () {

                $pnUsers.removeClass('loading');
                //Update scrollpane after users loading	
                $('#personal-network').jScrollPane({
                    verticalDragMinHeight: 40,
                    verticalDragMaxHeight: 40
                });


                $('#pn-circles-rename-button').button({
                    icons: {
                        primary: "ui-icon-circle-arrow-n"
                    },
                    text: false
                });

                $('#pn-circles-remove-button').button({
                    icons: {
                        primary: "ui-icon-trash"
                    },
                    text: false
                });

                $('#pn-users-add-button').button({
                    icons: {
                        primary: "ui-icon-person"
                    },
                    text: false
                });



            });
        }



        /**
         * Build the Personal network jquery ui tab
         */
        function buildPersonalNetworkTab() {

            //Define jquery selector
            var $pnCircle = $('#pn-circles .pn-circle');
            var $pnManageButton = $('#personal-network-manage-button');
            var $addCircleButton = $('#pn-circles-add-button');
            var $renameCircleButton = $('#pn-circles-rename-button');
            var $removeCircleButton = $('#pn-circles-remove-button');
            var $addUserButton = $('#pn-users-add-button');
            var $removeUsersButton = $('#pn-users .pn-users-remove-button');

            //Action select circle
            $pnCircle.live('click', function () {
                if (!$(this).hasClass('ui-selected')) {
                    $('#pn-circles .pn-circle').removeClass('ui-selected');
                    $(this).addClass('ui-selected');

                    var id = $(this).attr('cnId');
                    selectedCnId = id;
                    loadPersonalNetworkDetails(id);

                }
            });
            //Action manage personal network 
            $pnManageButton.live('click', function (event) {
                event.preventDefault();
                openLoginDialog();
            });


            //Action create circle
            $addCircleButton.live('click', function () {
                getCircleDialog().attr('title', 'Create circle');
                getCircleDialog().find('#action').val('CREATE');
                getCircleDialog().dialog('option', 'title', 'Create a personal network');
                getCircleDialog().dialog('open');
            });

            //Action edit circle to rename
            $renameCircleButton.live('click', function (event) {
                event.preventDefault();
                var name = $('#pn-users-title  h2').text();
                getCircleDialog().find('#action').val('UPDATE');
                getCircleDialog().find('#name').val(name);
                getCircleDialog().dialog('option', 'title', 'Update a personal network');
                getCircleDialog().dialog('open');
            });


            //Action remove circle
            $removeCircleButton.live('click', function (event) {
                event.preventDefault();
                openConfirmDialog(function () {
                    if (selectedCnId != null && selectedCnId > 0) {
                        $.post(globalManageCircleNetworkUrl, {
                            action: 'REMOVE',
                            id: selectedCnId
                        }, function (data) {

                            selectedCnId = -1;
                            $profileTabs.tabs('load', PN_TAB_INDEX);
                            loadPersonalNetworkBlock();
                        });
                    }
                });
            });


            //Action remove user from circle
            $removeUsersButton.live('click', function () {
                var user = $(this).attr('user');
                openConfirmDialog(function () {
                    if (selectedCnId != null && selectedCnId > 0) {
                        $.post(globalManageCircleNetworkUrl, {
                            action: 'REMOVE_USER',
                            id: selectedCnId,
                            user: user
                        }, function (data) {
                            $profileTabs.tabs('load', PN_TAB_INDEX);
                            loadPersonalNetworkBlock();
                        });
                    }
                });

            });


            //Action manage personal network 
            $addUserButton.live('click', function (event) {
                event.preventDefault();
                $.showModalDialog({
                    url: globalFindPeoplePopupUrl,
                    dialogArguments: {},
                    height: 450,
                    width: 570,
                    resizable: false,
                    scrollable: false,
                    onClose: function () {
                        var user = this.returnValue;
                        if (user != null && user != '') {
                            $.post(globalManageCircleNetworkUrl, {
                                action: 'ADD_USER',
                                id: selectedCnId,
                                user: user
                            }, function (data) {
                                $profileTabs.tabs('load', PN_TAB_INDEX);
                                loadPersonalNetworkBlock();
                            });
                        }
                    }
                });
            });

        }


        /**
         * Build and open the create/update a personal network jquery ui dialog 
         */
        function buildCircleDialog() {
            var $nameField = $('#name');
            var $actionField = $('#action');

            //Define jquery selector
            var $allFields = $([]).add($nameField);
            var $tips = $('#circle-form .validateTips');
            var $circleForm = $('#circle-form');
            $circleForm.dialog({
                autoOpen: false,
                resizable: false,
                height: 200,
                modal: true,
                buttons: [{
                    text: 'Ok',
                    click: function (event) {
                        var name = $.trim($nameField.val());
                        if (name == '' || name == null || checkRegexAlphaNum(name)) {
                            updateTips($tips, 'Please enter a valid name.');
                            return;
                        }


                        var formObj = {
                            action: $actionField.val(),
                            id: selectedCnId,
                            name: name
                        };

                        var $dialog = $(this);
                        $.getJSON(globalManageCircleNetworkUrl, formObj, function (json) {
                            if (json.success) {
                                $profileTabs.tabs('load', PN_TAB_INDEX);
                                loadPersonalNetworkBlock();
                                $dialog.dialog('close');
                            } else {
                                updateTips($tips, 'Create or update personal network has failed.');
                            }
                        });


                    }
                }, {
                    text: 'Cancel',
                    click: function () {
                        $(this).dialog('close');
                    }
                }],
                close: function (event, ui) {
                    $allFields.val('');
                    $tips.html('');
                }
            }).keypress(function (e) {

                if (e.keyCode == 13) {

                    //if keypress enter, click on LoginButton
                    $(this).parent().find('button:nth-child(1)').trigger('click');
                }
            });

            return $circleForm;
        }

        /**
         * Build and open the login jquery ui dialog 
         */
        function openLoginDialog() {
            //Define jquery selector
            var $username = $('#username');
            var $password = $('#password');
            var $allFields = $([]).add($username).add($password);
            var $tips = $('#login-form .validateTips');
            var $loginForm = $('#login-form');


            $loginForm.dialog({
                autoOpen: true,
                resizable: false,
                height: 230,
                modal: true,
                buttons: [{
                    text: 'Login',
                    click: function () {
                        var formObj = {
                            username: $username.val(),
                            password: $password.val()
                        };

                        $.getJSON(globalPersonalNetworkLoginUrl, formObj, function (json) {
                            if (json.success) {
                                $(location).attr('href', globalPeopleDetailsUrl + json.gin + '&pn=true');
                                $(this).dialog('close');
                            } else {
                                updateTips($tips, 'Login failed. Please try again.');
                            }
                        });
                    }
                }, {
                    text: 'Cancel',
                    click: function () {
                        $(this).dialog('close');
                    }
                }],
                close: function (event, ui) {
                    $allFields.val('');
                    $tips.html('');
                }
            }).keypress(function (e) {
                if (e.keyCode == 13) {
                    //if keypress enter, click on LoginButton
                    $(this).parent().find('button:nth-child(1)').trigger("click");
                }
            });
        }


        /**
         * Build and open the confirm jquery ui dialog 
         */
        function openConfirmDialog(onConfirmDialog) {

            var $confirmForm = $('#dialog-confirm');
            $confirmForm.dialog({
                autoOpen: true,
                resizable: false,
                height: 230,
                modal: true,
                buttons: [{
                    text: "OK",
                    click: function () {
                        onConfirmDialog();
                        $(this).dialog('close');
                    }
                }, {
                    text: "Cancel",
                    click: function () {

                        $(this).dialog('close');
                    }
                }]
            });

            $confirmForm.keypress(function (e) {

                if (e.keyCode == 13) {
                    $(this).parent().find('button:nth-child(1)').trigger("click");
                }
            });

        }

        function checkRegexAlphaNum(str) {
            var myRegxp = /[^\w \xC0-\xFF]/g;
            return myRegxp.test(str);
        }

        function updateTips(tipsElt, t) {
            tipsElt.text(t);
            setTimeout(function () {
                tipsElt.text('');
            }, 2500);
        }


        function getCircleDialog() {

            if ($circleDialog == null) {
                $circleDialog = buildCircleDialog();
            }
            return $circleDialog;

        }



    };